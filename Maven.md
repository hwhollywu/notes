# Maven

Source:
- [Apache Maven](https://maven.apache.org/index.html)
- [Maven教程](https://www.yiibai.com/maven/)
- [Maven Tutorial](https://www.tutorialspoint.com/maven/maven_overview.htm)
- [How to do Java](https://howtodoinjava.com/maven/local-remote-central-repositories/)

Apache Maven : a software project management and comprehension tool for any Java based project. Maven project structure and contents are declared in an xml file, pom.xml, referred as Project Object Model (POM).

Maven helps to manage: 
Builds
Documentation
Reporting
Dependencies
SCMs
Releases
Distribution
Mailing list

## 1. Install Maven 
1. download Maven zip file from the official website, unzip it.
2. add M2_HOME or MAVEN_HOME and PATH in .bash_profile
```
$ vim ~/.bash_profile

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home
export JAVA_HOME=$JAVA_HOME
CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$JAVA_HOME/bin:$PATH:
M2_HOME=.../myapp/apache-maven-3.5.3
export PATH=$M2_HOME/bin:$PATH:
export PATH
```
3. check in Terminal if maven is installed.
```
$mvn -version
```

## 2. Local, Remote and Central Repositories

* Local Repository: .m2 by default. 
can change the location of the local repository in {M2_HOME}\conf\setting.xml file using localRepository tag.
```
<settings>
    <localRepository>
        C:\M2
    </localRepository>
</settings>
```
* Central Repository: [Original](http://repo.maven.apache.org/maven2/) [Search]( http://search.maven.org/)
By default, Maven will check the POM file and download the dependencies from the local repository; if can't find the dependency, Maven will search and download from the central repository.  
special configuration: [Maven Configure Network Proxy Settings](https://howtodoinjava.com/maven/configure-network-proxy-settings-for-maven/)
* Remote Repository: 
When an artifact is needed, it is first downloaded to developer’s local repository and then used.
Add in the POM file or super POM file in remote repository itself:
```
 <repositories>
	<repository>
	    <id>java.net</id>
	    <url>https://maven.java.net/content/repositories/public/</url>
	</repository>
    </repositories>
```

## 3. Add Dependencies 

If you want to use Log4j as the log of the project, you will do:
1. Go to [Log4j](http://logging.apache.org/log4j/)
2. Download Log4j's jar library
3. Copy jar to the project class path
4. Mannually include the denpendency of jar to the project 
5. Do all the management work

In Maven: add dependency in pom.xml, maven will do the download and management
```
<dependencies>
    <dependency>
	<groupId>log4j</groupId>
	<artifactId>log4j</artifactId>
	<version>1.2.14</version>
    </dependency>
</dependencies>
```
<groupid> group name, e.g. com.alibaba.umeng.com
<artifactid> project name, e.g. D-plus

If the jar is not located in the central reposotory:
e.g.  kaptcha: identifying code pacakge
1. download, unzip and move kaptcha 
2. mvn install, to copy kaptcha jar to the local repository untill "build successfully"
```
mvn install:install-file -Dfile=c:\kaptcha-{version}.jar -DgroupId=com.google.code -DartifactId=kaptcha -Dversion={version} -Dpackaging=jar

```
3. add dependency in pom.xlm
```
<dependency>
      <groupId>com.google.code</groupId>
      <artifactId>kaptcha</artifactId>
      <version>2.3</version>
 </dependency>
```

## 4. Build Java Project
1. In the repository of the Java project, create project using maven-archetype-quickstart model:
```
mvn archetype:generate -DgroupId={project-packaging} -DartifactId={project-name}-DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

maven-archetype-quickstart follow the following structure:
```
project-name
   |-src
   |---main
   |-----java
   |-------com
   |---------project-packaging   
   |-----------App.java
   |---test|-----java
   |-------com
   |---------project-packaging
   |-----------AppTest.java
   |-pom.xml
```
source code + unity test + pom.xml.
POM(Project Object Model) is the fundamental unit of work in Maven. It is an XML file that contains information about the project and configuration details used by Maven to build the project.

2. In the project directory, make it an Eclipse project:
```
C:...\project-name> $ mvn eclipse:eclipse
```
In Eclipse: “File -> Import… -> General->Existing Projects into Workspace”

3. update POM.xml
dependencies, plugins 

e.g. JUnit 4.11
```
<dependency>
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
	<version>4.11</version>
	<scope>test</scope>
</dependency>
```

## 5. Build Web Project 
1. use Maven template
```
$ mvn archetype:generate -DgroupId=com.something -DartifactId=CounterWebApp -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```
maven-archetype-webapp structure:
```
.|____CounterWebApp
||____pom.xml
||____src
|||____main
||||____resources
||||____webapp
|||||____index.jsp
|||||____WEB-INF
||||||____web.xml

After updating:
.|____CounterWebApp
|____pom.xml
|____src
| |____main
| | |____java
| | | |____com
| | | | |____alibaba
| | | | | |____controller
| | | | | | |____BaseController.java
| | |____resources
| | | |____logback.xml
| | |____webapp
| | | |____WEB-INF
| | | | |____mvc-dispatcher-servlet.xml
| | | | |____pages
| | | | | |____index.jsp
| | | | |____web.xml
```
=> web.xml(Servlet), pom.xml & index.jsp (hello world html)

2. build in Eclipse:
```
C:\worksp\project-name>mvn eclipse:eclipse -Dwtpversion=2.0
```
 -Dwtpversion=2.0: Tell Maven to use Eclipse's Web project(WAR) instead of JAVA(JAR)

 3. update POM.xml

Add dependencies:  Spring, logback and JUnit

```
	...
	<name>CounterWebApp Maven Webapp</name>
	<url>http://maven.apache.org</url> <properties>
		<jdk.version>1.7</jdk.version>
		<spring.version>4.1.1.RELEASE</spring.version>
		<jstl.version>1.2</jstl.version>
		<junit.version>4.11</junit.version>
		<logback.version>1.0.13</logback.version>
		<jcl-over-slf4j.version>1.7.5</jcl-over-slf4j.version>
	</properties>

	<dependencies>
		<!-- Unit Test -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>${junit.version}</version>
		</dependency>

		<!-- Spring Core -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>${spring.version}</version>
			<exclusions>
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${jcl-over-slf4j.version}</version>
		</dependency>
		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-classic</artifactId>
			<version>${logback.version}</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>${spring.version}</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<!-- jstl -->
		<dependency>
			<groupId>jstl</groupId>
			<artifactId>jstl</artifactId>
			<version>${jstl.version}</version>
		</dependency>
	</dependencies>

	<build>
		<finalName>CounterWebApp</finalName>

```
4. update source code
Add Spring MVC files and logback Framework:

Spring MVC:
/src/main/java/com/alibaba/controller/BaseController.java

Spring configuration:
/src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml

Servlet 2.5 + Spring Framework ContextLoaderListener
/src/main/webapp/WEB-INF/web.xml

Move index.jsp to WEB-INF/pages, to protect it from direct visiting
/src/main/webapp/WEB-INF/pages/index.jsp

Add logback.xml to resources 
/src/main/resources/logback.xml

Add Tomcat plugin to pom.xml

```
mvn package 
mvn tomcat:run
```



