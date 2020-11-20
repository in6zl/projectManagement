# 末端供应链

## 项目结构
- esc-core **核心模块**
- esc-service **业务逻辑模块**
    - esc-service-sys **系统模块**
    - esc-service-factory **厂商模块**
    - esc-service-supplier **供应商模块**
- esc-task **任务模块**
    - esc-message **消息处理模块**
    - esc-schedule **定时任务**
    - esc-publish **消息推送服务**
- esc-api **App/微信/小程序等接口服务**
- esc-server **主服务包含后端 + Vue前端工程**

## 打包方式
```
mvn clean:clean package -Dmaven.test.skip=true -Pprod
```

## 开放环境搭建
```
docker-compose up
```
