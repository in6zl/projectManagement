package com.pactera.esc.core.util;

import com.pactera.esc.core.annotations.EntityName;
import com.pactera.esc.core.exception.BaseException;
import com.sun.istack.Nullable;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.PersistenceContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

@Component
public class EntityDependencyCache implements CommandLineRunner{
    //类依赖map
    public static HashMap<Class,List<Class>>  classDependenceMap=new HashMap<>();
    //类-实体map
    public static HashMap<Class,JpaRepository> classBeanMap=new HashMap<>();
    //类-查询map
    public static HashMap<Class,List<String>> classQueryMap=new HashMap<>();
    @Override
    public void run(String... args) throws Exception {

        Set<Class> classSet= ClassScaner.scan("com.pactera.esc.core.domain", null);
        for(Class singleClass:classSet){
            //检测扫描到类是否存在外部类，如果存在则表明自身是内部类，忽略，该问题主要由于@Builder会构建一个内部类
            if(singleClass.getEnclosingClass()!=null){
                continue;
            }
            String tableName="";
            Class tmpClass=singleClass;
            //循环赋值主要解决子类没有@Entity的问题
            while (tmpClass !=null && !tmpClass.getName().toLowerCase().equals("java.lang.object")) {
                Annotation[] classAnnotation=tmpClass.getAnnotations();
                for(Annotation annotation:classAnnotation){
                    if(annotation.annotationType() == Entity.class){
                        tableName=((Entity)annotation).name();
                    }
                }
                if("".equals(tableName)){
                    tmpClass = tmpClass.getSuperclass(); //得到父类,然后赋给自己
                }else{
                    break;
                }
            }



            Field[] fields=singleClass.getDeclaredFields();
            for (Field field : fields) {
                //忽略基本字段，Collection主要是忽略多对多和一对多的情况
                if (!(field.getType().isPrimitive() || field.getType() == String.class
                    || field.getType() == Integer.class || field.getType() == Double.class
                    || field.getType() == Float.class || field.getType() == Long.class
                    || field.getType() == Short.class || field.getType() == Byte.class
                    || field.getType() == Boolean.class || field.getType() == Character.class
                    || field.getType().isInstance(Collection.class)|| field.getType().isInstance(Map.class)
                    || field.getType().isInstance(Arrays.class))) {
//                    Class keyClass=SpringUtils.getBean(field.getName()).getClass();
                    //填充被依赖类与类之间的关系
                    if(classDependenceMap.get(field.getType())!=null){
                        classDependenceMap.get(field.getType()).add(singleClass);
                    }else{
                        List<Class>  classList=new ArrayList<>();
                        classList.add(singleClass);
                        classDependenceMap.put(field.getType(),classList);
                    }

                    //构建被依赖类与类查询的关系
                    Annotation[] annotations=field.getDeclaredAnnotations();
                    List<Annotation> annotationList=Arrays.asList(annotations);
                    String query="select  * from "+tableName+" where ";

                    for(Annotation annotation:annotationList){
                        if(annotation.annotationType() == JoinColumn.class){
                            query+=((JoinColumn)annotation).name()+" = " ;
                            break;
                        }
                    }
                    if(classQueryMap.get(field.getType())!=null){
                        classQueryMap.get(field.getType()).add(query);

                    }else{
                        List<String>  stringList=new ArrayList<>();
                        stringList.add(query);
                        classQueryMap.put(field.getType(),stringList);
                    }
                }
            }
        }

        Set<Class> classDaoSet= ClassScaner.scan("com.pactera.esc.core.dao", null);
        for(Class classDao:classDaoSet){
            JpaRepository jpaRepository=(JpaRepository) SpringUtils.getBean(classDao);
            Type[] types=classDao.getGenericInterfaces();
            for(Type type:types){
                //判断父类是不是参数化的类型，如果是强转成ParameterizedType
                if(type instanceof ParameterizedType){
                    ParameterizedType parameterizedType = (ParameterizedType)type;
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    Class<?> clazz = (Class<?>)actualTypeArguments[0];
                    System.out.println(clazz);
                    classBeanMap.put(clazz,jpaRepository);
                    break;
                }
            }
        }
    }
    /**
     * 检查类是否存在被依赖情况的方法，该方法为jpa版本，
     * 好处为便于数据库移植，主要问题为当引用类的表数量很大时容易造成查询缓慢或者内存溢出的问题
     * @param obj 需要检查是否存在被依赖情况的对象
     * @param ignoreClasses 忽略检查的类，在关联删除的时候使用
     * **/
    public static void validateDependence(Object obj, @Nullable Class... ignoreClasses){
       try {
//           Field[] fields = obj.getClass().getDeclaredFields();
           Class tmpClass=obj.getClass();
           List<Field> fields = new ArrayList<>() ;
           while (tmpClass !=null && !tmpClass.getName().toLowerCase().equals("java.lang.object")) {
               fields.addAll(Arrays.asList(tmpClass.getDeclaredFields()));
               tmpClass = tmpClass.getSuperclass(); //得到父类,然后赋给自己
           }
           String id = null;
           //除id字段外，全部置空
           for (Field field : fields) {
               field.setAccessible(true);
               if (field.getName().equals("id")) {
                   try {
                       id = field.get(obj).toString();
                   } catch (Exception e) {
                       continue;
                   }
               }else{
                   //检查字段是否final的情况，该情况无法赋值,解决序列化id时final的情况
                   if(!Modifier.isFinal(field.getModifiers())){
                       field.set(obj,null);
                   }
               }
           }


           if (classDependenceMap.get(obj.getClass()) != null && id != null) {
               List<Class> ignoreClassList=Arrays.asList(ignoreClasses);



               for (Class singleClass : classDependenceMap.get(obj.getClass())) {
                   if(ignoreClassList.contains(singleClass)){
                     continue;
                   }
                   Object object = singleClass.newInstance();
                   Field[] fields1 = object.getClass().getDeclaredFields();
                   for (Field field1 : fields1) {
                       if (field1.getType().isInstance(obj)) {
                           field1.setAccessible(true);
                           field1.set(object, obj);
                       }
                   }
                   Example example = Example.of(object);
                   List list = classBeanMap.get(singleClass).findAll(example);
                   Annotation[] annotations = singleClass.getAnnotations();


                   if (list != null && list.size() > 0) {
                       String tablename = "";
                       for (Annotation annotation : annotations) {
                           if (annotation.annotationType() == Entity.class) {
                               tablename = ((Entity) annotation).name();
                           }
                           if (annotation.annotationType() == EntityName.class) {
                               tablename = ((EntityName) annotation).name();
                           }
                           break;
                       }
                       throw new BaseException("01", "删除失败," + tablename + "存在记录");
                   }
               }
           }
       }catch (IllegalAccessException e){
           throw new BaseException("01", "删除失败,系统验证依赖过程出错");
       }catch (InstantiationException e){
           throw new BaseException("01", "删除失败,系统验证依赖过程出错");
       }catch (BaseException e){
           throw  e;
       }
    }

    @PersistenceContext
    private  EntityManager entityManager;

    /**
     * 检查类是否存在被依赖情况的方法，该方法为原生查询版本，
     * 现查询支持的为mysql版本，无法移植，主要解决性能问题，
     * 使用此方法为防止大表查询缓慢需要在表做索引等数据库优化措施
     * @param obj 需要检查是否存在被依赖情况的对象
     * @param ignoreClasses 忽略检查的类，在关联删除的时候使用
     * **/
    public  void validateDependenceNativeQuery(Object obj, @Nullable Class... ignoreClasses){
        try {
            Class tmpClass=obj.getClass();
            List<Field> fields = new ArrayList<>() ;
            while (tmpClass !=null && !tmpClass.getName().toLowerCase().equals("java.lang.object")) {
                fields.addAll(Arrays.asList(tmpClass.getDeclaredFields()));
                tmpClass = tmpClass.getSuperclass(); //得到父类,然后赋给自己
            }
            String id = null;
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    try {
                        id = field.get(obj).toString();
                    } catch (Exception e) {
                        continue;
                    }
                }else{
                    if(!Modifier.isFinal(field.getModifiers())){
                        field.set(obj,null);
                    }
                }
            }


            if (classQueryMap.get(obj.getClass()) != null && id != null) {
                Session session = (Session) entityManager.getDelegate();
                for(String query:classQueryMap.get(obj.getClass())){
                    NativeQuery nativeQuery =session.createSQLQuery(query+" '"+id+"' limit 1");
                    List list=nativeQuery.list();
                    if(list!=null&&list.size()!=0){
                        String tablename=(query.split("from")[1]).split("where")[0];
                        throw new BaseException("01", "删除失败," + tablename + "存在记录");
                    }
                }

            }
        }catch (IllegalAccessException e){
            throw new BaseException("01", "删除失败,系统验证依赖过程出错");
        }catch (BaseException e){
            throw  e;
        }
    }
}
