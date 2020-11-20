package com.pactera.esc.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

/**
  * <p>Title: BuildIdFactory.java</p>
  * <p>Description: 分布式生成id工具  </p>
  * @author zhangmingji
  * @email 17621505328@163.com
  * @date 2018年10月25日
  */
public class BuildIdFactory {


	 /**
     * 序列
     */
    private final static String TAB_ORDER = "order";
    private static volatile IdGenerator idGenerator;
    private static volatile BuildIdFactory instance;

	public static BuildIdFactory getInstance() {
        if(idGenerator == null) {
        	synchronized (IdGenerator.LoadIdGeneratorConfig.class) {
        		try {
        			//初始化
        			idGenerator = IdGenerator.LoadIdGeneratorConfig.loadConfig.buildIdGenerator();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
			}
        }
        if(instance == null ) {
        	synchronized (BuildIdFactory.class) {
        		instance = new BuildIdFactory();
			}
        }
        return instance;
    }

	public static String getId() {
        return  getInstance().buildFactoryOrderId();
    }

	 public String buildFactoryOrderId(String tab) {
		 return idGenerator.next(tab);
	  }
	 public String buildFactoryOrderId() {
		 return idGenerator.next(TAB_ORDER);
	  }

}
@Component
final class IdGenerator{

    private static String host1;

    private static String port1;

    private static String pass1;

    public String getHost1() {
        return host1;
    }

    @Value("${spring.redis.host}")
    public void setHost1(String host1) {
        this.host1 = host1;
    }

    public String getPort1() {
        return port1;
    }

    @Value("${spring.redis.port}")
    public void setPort1(String port1) {
        this.port1 = port1;
    }

    public static String getPass1() {
        return pass1;
    }
    @Value("${spring.redis.password}")
    public static void setPass1(String pass1) {
        IdGenerator.pass1 = pass1;
    }

    static final Logger logger = LoggerFactory.getLogger(IdGenerator.class);
    /**
     * JedisPool, luaSha
     */
    List<Pair<JedisPool, String>> jedisPoolList;
    int retryTimes;//重试次数
    int index = 0;//多个配置从0开始读取

    public IdGenerator() {

    }

    private IdGenerator(List<Pair<JedisPool, String>> jedisPoolList, int retryTimes) {
        this.jedisPoolList = jedisPoolList;
        this.retryTimes = retryTimes;
    }

    static public IdGeneratorBuilder builder() {
        return new IdGeneratorBuilder();
    }

    static class IdGeneratorBuilder {


        List<Pair<JedisPool, String>> jedisPoolList = new ArrayList<>();
        int retryTimes = 5;

        public IdGeneratorBuilder addHost(String host, int port, String pass, String luaSha) {
            JedisPoolConfig config = new JedisPoolConfig();
            //最大空闲连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
            config.setMaxIdle(200);
            //最大连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
            config.setMaxTotal(300);
            config.setTestOnBorrow(false);
            config.setTestOnReturn(false);
            config.setLifo(true);
            config.setMinIdle(30);
            jedisPoolList.add(Pair.of(StringUtils.isEmpty(pass) ? new JedisPool(config, host, port, 1000) : new JedisPool(config, host, port, 1000, pass), luaSha));
            return this;
        }

        public IdGeneratorBuilder retryTimes(int retryTimes) {
            this.retryTimes = retryTimes;
            return this;
        }

        public IdGenerator build() {
            return new IdGenerator(jedisPoolList, retryTimes);
        }
    }

    public String next(String tab) {
    	//自定义设置的重试次数 直到获取id
        for (int i = 0; i < retryTimes; ++i) {
            String id = innerNext(tab);
            if (id != null) {
                return id;
            }
        }
        //获取不到用原来的方法获取id
        return null;
    }

    private String innerNext(String tab) {
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month=String.valueOf(cal.get(Calendar.MONTH)+1);
        String day= String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
//        String day = String.valueOf(cal.get(Calendar.DAY_OF_YEAR));
        if (index == jedisPoolList.size()){
            index = 0;
        }

        Pair<JedisPool, String> pair = jedisPoolList.get(index++ % jedisPoolList.size());
        JedisPool jedisPool = pair.getLeft();
        String luaSha = pair.getRight();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = Long.valueOf(jedis.evalsha(luaSha, 4, tab, year,day,month).toString());
            return String.valueOf(result);
        } catch (JedisException e) {
        	//获取不到用原来的方法获取id
        	logger.error("redis获取 id error!", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    static class LoadIdGeneratorConfig {

        static List<RedisScriptConfig> scriptConf = new ArrayList<>();
        static LoadIdGeneratorConfig loadConfig = new LoadIdGeneratorConfig();
        //读取redis ip和端口密码配置 放入list里
        static {
             try {
                 scriptConf.add(new RedisScriptConfig(host1, Integer.valueOf(port1), pass1));
             }catch (Exception e) {
                 e.printStackTrace();
             }
        }

        public IdGenerator buildIdGenerator() throws IOException {
            loadConfig.loadScript();
            IdGeneratorBuilder idGenerator = IdGenerator.builder();
            for (RedisScriptConfig conf : scriptConf) {
                idGenerator = idGenerator.addHost(conf.getHost(), conf.getPort(), conf.getPass(), conf.getScriptSha());
            }
            return idGenerator.build();
        }
        //加载lua脚本配置 获取sha
        public void loadScript()
            throws IOException {
            int index = 1;
            for (RedisScriptConfig conf : scriptConf) {
                Jedis jedis = new Jedis(conf.getHost(), conf.getPort());
                if(!StringUtils.isEmpty(conf.getPass())) {
                	jedis.auth(conf.getPass());
                }
                InputStream is = LoadIdGeneratorConfig.class.getResourceAsStream("/redisLua/redis-script-node" + index++ + ".lua");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String readLine = null;
                StringBuilder sb = new StringBuilder();
                while ((readLine = br.readLine()) != null) {
                    sb.append(readLine);
                }
                br.close();
                is.close();
                conf.setScriptSha(jedis.scriptLoad(sb.toString()));
                jedis.close();
            }
        }
    }
    //redis配置实体类
    static class RedisScriptConfig {

        private String host;
        private Integer port;
        private String pass;
        private String scriptSha;

        public RedisScriptConfig(String host, Integer port, String pass) {
            super();
            this.host = host;
            this.port = port;
            this.pass = pass;
        }

        public void setScriptSha(String scriptSha) {
            this.scriptSha = scriptSha;
        }

        public String getHost() {
            return host;
        }

        public Integer getPort() {
            return port;
        }

        public String getPass() {
            return pass;
        }

        public String getScriptSha() {
            return scriptSha;
        }

    }
}
