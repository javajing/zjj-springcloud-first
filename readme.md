# 前言 #

springcloud第一代组件产品的最基本使用demo, 
后续我将继续更新cloud二代(springcloud-second),
 
 cloud alibaba(springcloud-my-alibaba) 的使用demo,以及目前流行的组件组合(springcloud-manager)使用的微服务项目实战;

# 一、准备工作 #


## 1.1配置虚拟主机映射 ##

eureka和config工程将为其他模块提供服务注册调用,所以需要进行服务的ip域名映射;

hosts文件路径：`C:\Windows\System32\drivers\etc`

根据部署环境增加以下host域名：

```
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
127.0.0.1 eureka7003.com
127.0.0.1 config-4001.com
```

## 1.2数据库 ##

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


# 2.0通用API和COMMON #

api工程创建Dept的映射实体，各个模块都可以使用了(不用每个微服务都创建一个Dept对象)。同时Service接口，Feign的代码也在那里编写。

- myfirst-rest-api

common工程做通用的dao底层交互的封装,通用的配置封装,和通用的工具类封装,供其他所有需要依赖的模块调用

- myfirst-common

# 2.1服务注册中心 #


Eureka服务注册中心(集群)

- myfirst-eureka-node1
- myfirst-eureka-node2
- myfirst-eureka-node3


## 2.2服务提供方 ##

Eureka服务提供方(集群)

- myfirst-provider-db1
- myfirst-provider-db2
- myfirst-provider-db3


带有hystrix功能的服务提供方(hystrix Demo)：

- myfirst-provider-hystrix

## 2.3服务消费方 ##

使用restTemplate+ribbon的方式来测试(ribbon自定义了负载均衡算法)

- myfirst-client-ribbon


使用feign来调用远程服务

- myfirst-client-feign(直接调用myfirst-rest-api的service接口)


监控消费方的指标(性能)

- myfirst-hystrix-dashboard


使用方式：

- 启动myfirst-hystrix-dashboard项目，打开`http://localhost:9003/hystrix.stream`


![](https://i.imgur.com/yoDFMHg.png)

我们可以监控：`myfirst-provider-hystrix`这个项目，于是在输入栏输入`http://localhost:8004/hystrix.stream`

![](https://i.imgur.com/pBhQJkD.png)

随后，我们去测试接口：`http://localhost:8004/dept/get/7`


监控的数据就会变化了：

![](https://i.imgur.com/ITs9WPS.png)



## 2.4网关 ##


用于转发路由，服务过滤(安全验证)，限流等等：

- myfirst-zuul

## 2.5Cloud配置文件 ##


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

- myfirst-config-server


SpringCloud Config 客户端：

- myfirst-config-read-test(测试读取config)
- myfirst-config-provider(从config读取配置并作为服务提供方)
- myfirst-config-eureka(从config读取配置并作为eureka注册中心)
