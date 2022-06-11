## 常用依赖
```xml
<dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.0.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
    </dependencies>
```

## 注解
- @AutoWired：自动装配通过类型，名字
    如果Autowired不能唯一自动装配上属性，则需要@Qualifier(value = "对象名")
- @Nullable：表示该字段可以为空  @Autowired(required = false)作用一样
- @Resource：自动装配通过名字、类型

- @Component：放在类上，表示spring已接管这个类，是bean
###### MVC衍生注解
@Component有几个衍生注解，按照MVC三层架构分层
- @Controller  Controller层
- @Repository  Dao层
- @Service     Service层
功能和@Component一样都是注册类到spring里，仅仅是为了分层。


##spring-mybatis整合

spring-dao.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!--ignore-unresolvable="true" 忽略解析不到的${}-->
    <context:property-placeholder ignore-unresolvable="true" location="classpath:db.properties"/>

    <!--DataSource:使用Spring的数据源替换Mybatis的位置  c3p0  dbcp  druid-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="${driver}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${name}"/>
        <property name="password" value="${password}"/>
    </bean>

    <!--sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--绑定mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/zjh/mapper/*.xml"/>
    </bean>

    <!--SqlSessionTemplate就是原来的sqlSession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--只能使用构造器注入 因为没有set方法-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>


</beans>
```

applicationContext.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <import resource="spring-dao.xml"/>

    <bean id="UserMapper2" class="com.zjh.mapper.UserMapperImpl2">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>

</beans>
```

mybatis-config.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--    导入外部配置文件-->
    <!--    <properties resource="db.properties"/>-->
    <!--    日志-->
    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
    <!--    起别名-->
    <typeAliases>
        <package name="com.zjh.pojo"/>
    </typeAliases>
    <!--    <environments default="development">-->
    <!--        <environment id="development">-->
    <!--            <transactionManager type="JDBC"/>-->
    <!--            <dataSource type="POOLED">-->
    <!--                <property name="driver" value="${driver}"/>-->
    <!--                <property name="url" value="${url}"/>-->
    <!--                <property name="username" value="${username}"/>-->
    <!--                <property name="password" value="${password}"/>-->
    <!--            </dataSource>-->
    <!--        </environment>-->
    <!--    </environments>-->

    <!--    <mappers>-->
    <!--        <mapper resource="com/zjh/mapper/UserMapper.xml"></mapper>-->
    <!--    </mappers>-->
</configuration>
```

UserMapperImpl.java
```java
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {

    public List<User> selectUser() {
        return getSqlSession().getMapper(UserMapper.class).selectUser();
    }
}
```

UserMapper.java
```java
public interface UserMapper {
    List<User> selectUser();
}
```

UserMapper.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.zjh.mapper.UserMapper">
    
    <select id="selectUser" resultType="user">
        select * from mybatis_test.user
    </select>
    
</mapper>
```
