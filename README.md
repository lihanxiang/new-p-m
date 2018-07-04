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

![](https://upload-images.jianshu.io/upload_images/3426615-a5f765667effbb14.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

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
> 2.在IDEA中打开项目,配置web项目信息 [Issue #1](https://github.com/lihanxiang/p-m/issues/1)
> 3.部署到Tomcat [Issue #1](https://github.com/lihanxiang/p-m/issues/1)
> 4.修改 db.properties 文件中的数据库信息，确保能连接数据库 
> 5.Run, 打开浏览器并输入地址： localhost:8080


//TODO