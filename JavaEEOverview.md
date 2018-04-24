
# Java EE Overview

Source: 
[JavaEE介绍](https://blog.csdn.net/xinxin19881112/article/details/4523274)

![JavaEE Menu](https://github.com/hwhollywu/notes/blob/master/JavaEEOverview.jpg?raw=true)

## 1. 3 Versions of Java Languages
* JavaME - Java Platform Micro Edition 微型版 小型设备和智能卡
* JavaSE - Java Platform Standard Edition，Java标准版 桌面版
* JavaEE - Java Platform Enterprise Edition 企业版
(J2EE = Java 2 Platform Enterprise Edition)

## 2. why Java EE:
differentiate service code and functional code, more sustainable and ductile

## 3. Framework/Tech
Web Service、Struts、Hibernate、Spring、JSP、Servlet、JSF、EJB、JavaBean、JDBC、JNDI、XML、JavaSE

Framework based on JavaEE, applications based on frameworks.

## 4. Layers of Java EE system
* 表示层：UI,UX
* 中间层：service, functional code
* 数据层：database
* +系统集成的技术
* Why: 重复代码减少，独立修改不同层

### 4.1 表示层技术： 
html, javascript, ajax = asynchronous javascript and XML
在无需加载网页的情况下更新部分技术、快速动态网页

### 4.2 中间层：
* JSP = Java server pages 服务页面，类XML的tags 
* Servlet = server applet 服务连接器、接受响应get-post, 
([More about Servlet](https://www.cnblogs.com/whgk/p/6399262.html))
* JSTL=java server pages standard tag library标签库 <%@ taglib prefix=“…” %> , 
* JavaBean = 模型组件, 有serializable接口,getter\setter, all private
* Struts = 拓展servlets, MVC(model, view, controller)模式

### 4.3数据层：
* JDBC= java database connectivity 执行sql语句的java api
* Hibernate:  对象封装JDBC

### 4.4 系统集成技术：
* JAX-WS = Java API for XML Web Service
* JNDI = Java Naming and Directory Interface
