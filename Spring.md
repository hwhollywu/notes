# Spring

Sources: Apress Pro Spring 4th Edition


# Chap 1. Introducing Spring

## What is Spring?
* a lightweight framework for building Java applications. 
* can use Spring to build ANY application in Java (stand-alone, web, or Java Enterprise Edition (JEE) applications)
* lightweight = minimal impact. make few changes to gain the benefits of the Spring core.

## Spring core:
### Inversion of Control (IoC): 
externalizes the creation and management of component dependencies. Dependency Injection (DI): an instance of a subclass is provided to superclass at runtime by some external process. Based on JavaBeans( (POJOs) and interfaces.

#### Different ways in defining dependency configuration:
XML files, Java configuration classes, annotations within your code, or the new Groovy bean definition. 

#### Bean: any Spring-managed resource.

#### Using DI & interfaces: mutually beneficial. 

#### Benefits of using DI:
1. Reduced glue code (combining components together.)
2. Simplified application configuration
3. Ability to manage common dependencies in a single repository
4. Improved testability (replace dependencies easily)
5. Fostering of good application design (against interfaces)
Drawback: difficult to see what implementation of a particular dependency is being hooked into which objects for someone who's not familiar with DI.

##### Spring Framework 4 supports Java 8: lambda expressions and method references with Spring’s callback interfaces.

## AOP(Aspect-Oriented Programming)
implement crosscutting logic: logic that applies to many parts of (across) your application automatically.
Spring creates dynamic proxies to the target objects &  weaves the objects with the configured advice to execute the crosscutting logic.

## Expression Language (EL) 
 a technology allows an application to manipulate Java objects at runtime. Unified Expression Language solved the problem that different technologies(e.g. Java Server Pages (JSP) and Java Server Faces (JSF)) provide different EL implementations and syntaxes. -> Spring Expression Language (SpEL) version 3.0

## Validation
a built-in validation API, Validator interface, which allows encapsulating validation logic into a class responsible for validating the target object. Also maintains an Errors object & ValidationUtils utility class. 





# Chap 2. Getting Started
# Chap 3. Introducing IoC and DI in Spring
# Chap 4. Spring Con guration in Detail
# Chap 5. Introducing Spring AOP
# Chap 6. Spring JDBC Support 
# Chap 7. Using Hibernate in Spring
# Chap 8. Data Access in Spring with JPA2
# Chap 9. Transaction Management
# Chap 10. Validation with Type Conversion and Formatting
# Chap 11. Task Scheduling in Spring
# Chap 12. Using Spring Remoting 
# Chap 13. Spring Testing
# Chap 14. Scripting Support in Spring
# Chap 15. Spring Application Monitoring
# Chap 16. Web Applications with Spring
# Chap 17. WebSocket
# Chap 18. Spring Projects: Batch, Integration, XD, and Boot











