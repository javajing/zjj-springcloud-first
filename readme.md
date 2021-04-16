# 前言 #

springcloud第一代组件产品的最基本使用demo, 
后续我将继续更新cloud二代(springcloud-second),
 
 cloud alibaba(springcloud-my-alibaba) 的使用demo,以及目前流行的组件组合(springcloud-manager)使用的微服务项目实战;

# 一、准备工作 #


## 1.1 配置虚拟主机映射 ##

eureka和config工程将为其他模块提供服务注册调用,所以需要进行服务的ip域名映射;\
hosts文件路径：`C:\Windows\System32\drivers\etc`;\
根据部署环境增加以下host域名：
```
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
127.0.0.1 eureka7003.com
127.0.0.1 config-4001.com
127.0.0.1 eureka5001.com
127.0.0.1 eureka5002.com
```

## 1.2 数据库 ##

创建三个数据库

```sql
create database cloudDB01;
create database cloudDB02;
create database cloudDB03;
```

三个库都创建相同的表
```sql
CREATE TABLE `dept` (
  `deptno` int NOT NULL AUTO_INCREMENT,
  `dname` varchar(50) DEFAULT NULL,
  `db_source` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
```
为表插入数据,db_source的值根据1,2,3**分别标识**(cloudDB01库对应的是1,cloudDB02库对应的是2)

```sql
-- database 1
INSERT INTO dept (deptno, dname, db_source) VALUES (1, 'zjj-db1-1', '1');
INSERT INTO dept (deptno, dname, db_source) VALUES (2, 'zjj-db1-2', '1');
INSERT INTO dept (deptno, dname, db_source) VALUES (3, 'zjj-db1-3', '1');
INSERT INTO dept (deptno, dname, db_source) VALUES (4, 'zjj-db1-4', '1');
INSERT INTO dept (deptno, dname, db_source) VALUES (5, 'zjj-db1-5', '1');

-- database 2
INSERT INTO dept (deptno, dname, db_source) VALUES (1, 'zjj-db2-1', '2');
INSERT INTO dept (deptno, dname, db_source) VALUES (2, 'zjj-db2-2', '2');
INSERT INTO dept (deptno, dname, db_source) VALUES (3, 'zjj-db2-3', '2');
INSERT INTO dept (deptno, dname, db_source) VALUES (4, 'zjj-db2-4', '2');
INSERT INTO dept (deptno, dname, db_source) VALUES (5, 'zjj-db2-5', '2');

-- database 3
INSERT INTO dept (deptno, dname, db_source) VALUES (1, 'zjj-db1-1', '3');
INSERT INTO dept (deptno, dname, db_source) VALUES (2, 'zjj-db3-2', '3');
INSERT INTO dept (deptno, dname, db_source) VALUES (3, 'zjj-db3-3', '3');
INSERT INTO dept (deptno, dname, db_source) VALUES (4, 'zjj-db3-4', '3');
INSERT INTO dept (deptno, dname, db_source) VALUES (5, 'zjj-db3-5', '3');
```

# 二、模块之间解释 #


# 2.0 通用API和COMMON #

api工程创建Dept的映射实体和接口申明，各个模块都可以使用了(不用每个微服务都创建一个Dept对象)。\
同时Service接口，Feign的代码也在那里编写。
- myfirst-rest-api

common工程做通用的dao底层交互的封装,通用的配置封装,和通用的工具类封装,供其他所有需要依赖的模块调用
- myfirst-common

# 2.1 服务注册中心 #

Eureka服务注册中心(集群)
- myfirst-eureka-node1(7001)
- myfirst-eureka-node2(7002)
- myfirst-eureka-node3(7003)

eureka服务启动后,可打开eureka的监控台,进行注册中心的集群查看以及服务注册信息的查看\
地址: `http://eureka7001.com:7001/`

![eureka](https://raw.githubusercontent.com/javajing/springcloud-first/master/readme/eureka.png)

## 2.2 服务提供方 ##

Eureka服务提供方(集群)
- myfirst-provider-db1(8001)
- myfirst-provider-db2(8002)
- myfirst-provider-db3(8003)


带有hystrix功能的服务提供方(hystrix Demo)：
- myfirst-provider-hystrix(8004)

## 2.3 服务消费方 ##

使用restTemplate+ribbon的方式来测试(ribbon自定义了负载均衡算法)
- myfirst-client-ribbon(9001)


使用feign来调用远程服务(直接调用myfirst-rest-api的service接口)
- myfirst-client-feign(9002)


监控消费方的指标(性能)
- myfirst-hystrix-dashboard(9003)

使用方式：
- 启动myfirst-hystrix-dashboard项目，打开`http://localhost:9003/hystrix.stream`


![hystrix1](https://raw.githubusercontent.com/javajing/springcloud-first/master/readme/hystrix1.png)

我们可以监控：`myfirst-provider-hystrix`这个项目，于是在输入栏输入`http://localhost:8004/hystrix.stream`

![hystrix2](https://raw.githubusercontent.com/javajing/springcloud-first/master/readme/hystrix2.png)

点击monitor进入8004应用的监控页面

![hystrix3](https://raw.githubusercontent.com/javajing/springcloud-first/master/readme/hystrix3.png)

随后，我们去测试接口：`http://localhost:8004/dept/get/2`, `http://localhost:8004/dept/get/12` 来触发正常请求和hystrix熔断请求并观察监控

可以看到监控的数据就会变化了：

![hystrix4](https://raw.githubusercontent.com/javajing/springcloud-first/master/readme/hystrix4.png)



## 2.4 网关 ##


用于转发路由，服务过滤(安全验证)，限流等等：

- myfirst-zuul(6001)

原本我们访问`myfirst-provider-hystrix`这个项目获取组织详情的接口为`http://localhost:8004/dept/get/1`\

这里我们通过zuul做统一的网关入口,加上统一服务访问前缀:zjj,和统一的服务路由path:/mydept/**\
现在我们访问获取详情机构接口为`http://localhost:6001/zjj/mydept/dept/get/1`

## 2.5 Cloud配置文件 ##


git脚本回顾：

```git

1. git init //初始化仓库

2. git add .(文件name) //添加文件到本地仓库

3. git commit -m "first commit" //添加文件描述信息

4. git remote add origin + 远程仓库地址 //链接远程仓库，创建主分支

5. git pull origin master --allow-unrelated-histories  // 把本地仓库的变化连接到远程仓库主分支

6. git push -u origin master //把本地仓库的文件推送到远程仓库

```

SpringCloud Config服务端(获取配置都从这里来拿)

- myfirst-config-server(4001)


SpringCloud Config 客户端：

测试读取config配置中的信息并指定cloud.config.profile为dev,注意pom中不可以引入config-server,只需要引入starter-config的客户端就行
- myfirst-config-read-test(5001)


从config读取配置并作为eureka注册中心,指定cloud.config.profile为test,那么注册中心启动后的端口为5002
- myfirst-config-eureka(5002)(从config读取配置并作为eureka注册中心)


从config读取配置, 将myfirst-config-eureka(5002)作为注册中心, 提供服务查询
- myfirst-config-provider(5001)
