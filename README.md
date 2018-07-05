# SSM 版本的简易商品管理系统

## Spring + SpringMVC + Mybatis 重构原有项目，在不改变行为的情况下使用框架开发

### 关于这个项目

学完基本的 Web 知识之后，就开始学习 SSM 的基本知识，其中穿插项目的构建，所以这一个项目进行了两周时间

## 版本 0.1

### 在此之前需要学习的知识

> Spring 基本概念（Bean，IOC），这个项目还是初级的版本，还没有用到 AOP，所以可以先跳过<br>
> SpringMVC 框架（模型，控制器）<br>
> Mybatis（基础增删改查，逆向工程）
> Mybatis-Spring 整合

### 准备工作

#### 1. 工程目录

![](https://upload-images.jianshu.io/upload_images/3426615-7b89a3fca08ce67a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* config：配置文件
* controller：控制器
* mapper 和 po 包是 Mybatis 逆向工程生成的代码
* service：服务，为控制器所调用

#### 2. JAR

使用 IDEA 创建 SpringMVC 工程，会自动下载相关文件，然后我们添加以下库：

* commons-beanutils-1.9.3
* commons-dbcp-1.4
* commons-pool-1.3
* itcast-tools-1.4
* jstl-1.2
* mybatis-3.4.6
* mybatis-spring-1.3.0
* mysql-connector-java-5.1.46

#### 3. 数据表

与 [p-m](https://github.com/lihanxiang/p-m)中一样，还是单表

#### 4. 如何使用

> 1.Clone 或 download 项目 https://github.com/lihanxiang/new-p-m.git<br>
> 2.在IDEA中打开项目,配置web项目信息 [Issue #1](https://github.com/lihanxiang/p-m/issues/1)<br>
> 3.部署到Tomcat [Issue #1](https://github.com/lihanxiang/p-m/issues/1)<br>
> 4.修改 db.properties 文件中的数据库信息，确保能连接数据库<br>
> 5.Run, 打开浏览器并输入地址： localhost:8080

### 开发过程

#### 1. Mybatis 和 Spring 的整合

参考[官方文档](http://www.mybatis.org/spring/zh/index.html)

之前添加的 mybatis-spring-1.3.0.jar 就是为了整合用的

整合这二者需要有两个东西：sqlSessionFactory 和 映射器类

首先，在 config 包内建立一个文件 db.properties 来存放数据库信息：

```
jdbc.driver = com.mysql.jdbc.Driver
jdbc.url = jdbc:mysql://localhost:3306/product
jdbc.username = 你自己的用户名
jdbc.password = 你自己的密码
```

然后，我们在 mybatis 包中创建一个 XML 文件 `applicationContext-dao.xml`，用来存放 Mybatis 的相关配置信息：

* 利用外部配置文件（db.properties）配置数据源：

```
    <context:property-placeholder location="classpath:config/db.properties"/>

    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <property name="maxIdle" value="10"/>
    </bean>
```

* sqlSessionFactory

在 Mybatis-Spring 中，sqlSessionFactory 以 Bean 的形式在文件中配置：

SqlSessionFactoryBean 用来构建 sqlSessionFactory（就像是 SqlSessionFactoryBuilder）

SqlSessionFactory 需要一个数据源，我们在这里引入上面配置的数据源：

```
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
    </bean>
```

接下来就是映射器类：

因为有不止一个 mapper，所以在这里没有配置具体的 mapper 信息，而是配置了一个 `MapperScannerConfigurer`，它会查找指定路径下的 mapper，并创建成 MapperFactoryBean

```
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="mapper"/>
    </bean>
```

这里还要说到的是，如果只有一个数据源的话，没有必要在 mapper 扫描器中添加 sqlSessionFactory

到此，Mybatis-Spring 的整合就结束了

#### 2. springMVC.xml

在这个文件里，我们配置组建扫描器，视图解析器，静态资源处理器：

在未配置静态资源处理器的情况下，对静态资源（这个项目里指的是背景图片）的访问会被拦截，所以我们用 mvc:resources 配置静态文件的位置，使得能够对其进行访问

```
    <!-- 组件扫描-->
    <context:component-scan base-package="controller"/>
    <context:component-scan base-package="service"/>

    <!-- 注解驱动 -->
    <mvc:annotation-driven/>

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!-- 静态资源处理器 -->
    <mvc:resources mapping="/image/**" location="/image"/>
```

#### 3. web.xml

先写配置文件的位置：

```
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/classes/config/spring/applicationContext-dao.xml</param-value>
    </context-param>
```

这里配置的是要装入 Spring 中的 Mybatis 的信息

然后我们需要创建监听器：

```
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
```

最后是 servlet 的配置：

```
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:config/spring/springMVC.xml</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>*.action</url-pattern>
    </servlet-mapping>
```

这里要注意的是，我们在这个 servlet 配置了一个只属于它的上下文信息：springMVC.xml

然后在 servlet-mapping 中设定对于所有请求都采用 xxx.action 的形式，这样符合 RESTful 风格

到此，所有需要配置的步骤都已完成，接下来是代码的部分了

#### 4. pojo 和 mapper

mybatis-spring 整合的项目中，映射器必须要为接口，而不是具体的实现类

之前我们说到，po 包和 mapper 包中都是 Mybatis 逆向工程生成的代码以及 SQL，但是这其中没有我们所要做的功能中的一项：查找

所以我们在 po 包中写一个自定义的 Product 类：

```
public class CustomizeProduct extends Product { }
```

什么都没有，为什么要这么做呢

Product类 对应的 ProductMapper 接口中的方法太多了，我这里新建一个，免得看花了眼

然后在 mapper 包中创建映射器接口以及映射器文件：

```
public interface CustomizeProductMapper {
    List<CustomizeProduct> productsList();
    List<CustomizeProduct> findProduct(CustomizeProduct customizeProduct);
}
```

这里的两个方法分别是显示所有商品和查找商品，然后根据接口写映射文件：

显示所有商品这没什么好说的：

```
    <select id="productsList" resultType="po.CustomizeProduct">
        SELECT * FROM product
    </select>
```

模糊查找，如果没有输入就显示所有商品：

```
    <select id="findProduct" parameterType="po.CustomizeProduct" resultType="po.CustomizeProduct">
        SELECT * FROM product
        <where>
            <if test="barcode != null and barcode != ''">
                barcode LIKE '%${barcode}'
            </if>
            <if test="name != null and name != ''">
                name = '%${name}%'
            </if>
            <if test="units != null and units != ''">
                units = #{units}
            </if>
            <if test="purchaseprice != null and purchaseprice != ''">
                purchaseprice LIKE #{purchaseprice}
            </if>
            <if test="saleprice != null and saleprice != ''">
                saleprice LIKE #{saleprice}
            </if>
            <if test="inventory != null and inventory != ''">
                inventory LIKE #{inventory}
            </if>
        </where>
    </select>
```

条形码的查找是只匹配尾部的，比如 3 只能匹配 123 而不能匹配 132

名字是前后匹配的，剩下的都是精确查找

#### 5. ProductService

Service 中定义了我们这个系统的所有功能：

```
public interface ProductService {
    
    void addProduct(Product product);
    
    void deleteProduct(String id);

    void updateProduct(Product product);

    Product findProductByID(String id);
    
    List<CustomizeProduct> productList();
    
    List<CustomizeProduct> findProduct(CustomizeProduct customizeProduct);
}
```