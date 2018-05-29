# Spring

Sources: Apress Pro Spring 4th Edition


## 1. Introduction

What is Spring?
* a lightweight framework for building Java applications. 
* can use Spring to build ANY application in Java (stand-alone, web, or Java Enterprise Edition (JEE) applications)
* lightweight = minimal impact. make few changes to gain the benefits of the Spring core.

Spring core:
* Inversion of Control (IoC): externalizes the creation and management of component dependencies. Dependency Injection (DI): an instance of a subclass is provided to superclass at runtime by some external process. Based on JavaBeans( (POJOs) and interfaces.

Different ways in defining dependency configuration:
XML files, Java configuration classes, annotations within your code, or the new Groovy bean definition. 

Bean: any Spring-managed resource.

Using DI & interfaces: mutually beneficial. 

Benefits of using DI:
1. Reduced glue code (combining components together.)
2. Simplified application configuration
3. Ability to manage common dependencies in a single repository
4. Improved testability (replace dependencies easily)
5. Fostering of good application design (against interfaces)
Drawback: difficult to see what implementation of a particular dependency is being hooked into which objects for someone who's not familiar with DI.










