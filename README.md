# WayToJava
*Where there is Java, there is a way to java.*
---
##项目成员及分工
*题目由左右成员共同确定。*
- 项目经理：王旭
- 美工+前端：金美辰和李玉琳
- 后台技术主管：曾晖
- 后台技术成员：万苗、于珏
- 注：武汉大学开源软件工程
##后端架构简介
本项目后端服务基于Spring Cloud实现了服务注册、服务发现、负载均衡、断路器、服务网关等一系列微服务架构，并基于Docker实现分布式部署。

###Eureka
Eureka是Spring Cloud中用于服务发现注册的组件，由Eureka服务器和Eureka客户端两部分组成：Eureka服务器用作服务注册；Eureka客户端可再分为服务提供方和服务消费方，服务提供方向Eureka服务器做服务注册、续约和下线等操作，服务消费方从Eureka服务器获取服务提供方的注册信息，并通过远程调用与其通信。
####1. way-to-java-eureka-server--服务注册
####2. way-to-java-eureka-client--具体服务-系统管理模块
####3. way-to-java-learning--具体服务--知识学习模块
####4. way-to-java-discussion--具体服务--讨论交流模块
###way-to-java-service-ribbon--服务消费及负载均衡
负载均衡Ribbon是 Spring Cloud 中实现客户端负载均衡的组件，该组件提供了多种负载均衡算法，包括简单轮询、随机、加权响应时间和区域感知轮询等。 Ribbon易于灵活扩展，可以实现自定义的负载均衡算法。
###way-to-java-service-zuul--路由转发及服务过滤
Zuul的主要功能是路由转发和过滤器。路由功能是微服务的一部分，比如／api/user转发到到user服务，/api/shop转发到到shop服务。zuul默认和Ribbon结合实现了负载均衡的功能。