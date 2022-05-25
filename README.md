# Demo project

## mandatory
* install IneliJ
* install jdk 8
* install tomcat9 locally

## setup
* create maven project (optional: use archtype maven-archetype-webapp)
* install 'smart tomcat' plugin

## setup server (tomcat)
### 1. install smart tomcat plugin
* open intelij
* in right top corner find 'IDE and project settings' icon
* click on icon and find plugins
* settings window opens
* on left side find 'plugins'
* in search bar write 'smart tomcat'
* install plugin
* click ok 

### 2. add tomcat 9 in project
* in top right corner, on  toolbar find 'configuration' dialog
* click on edit configuration
* 'run/debug configuration' window is open
* in left top corner find '+' icon and click 
* find 'smart tomcat'

* fill inputs with tomcat configurations
* tomcat server: find in files where your tomcat is locally saved
* deployment directory: ...yourProject/.../webapp
* server port and admin port leave default
* click ok

* now you can see green play button next to the 'configuration' dialog
* run tomcat 
* in logs will be link to page http://localhost:8080/
* your tomcat runs successfully 

#### pro tips
* to avoid local host problems click button 'windows' on your keyboard
* search for 'services'
* run services as admin
* on given list find apache tomcat 9.0 tomcat9 - double click
* in open window, under tab 'general', Stop server and put 'startup type' on 'Disabled'
* click ok

#### maybe needed: 
* locally find your tomcat folder, go to config and open tomcat-users file in notepad ++
* on line  38, between two comments paste next:
```ruby
  <role rolename="manager-gui"/>
  <user username="admin1" password="admin" roles="manager-gui"/>
  <user username="admin2" password="admin" roles="manager-script"/> 
```
* save file

### 3. Back to inteliJ

#### main.java folder
* in main folder create java.demo.app folder and create Java class 'HelloApplication'
* create also java.demo.rest folder and create Java HelloRestService class

HelloApplication.java :
```ruby
package demo.app;

// import the rest service you created!
import demo.rest.HelloRestService;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
public class HelloApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public HelloApplication() {
        // Register our hello service
        singletons.add(new HelloRestService());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
```
HelloRestService.java :
```ruby
package demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class HelloRestService {
    @GET // This annotation indicates GET request
    @Path("/hello")
    public Response hello() {
        System.out.println("ovdje nesto napisi  223");
        return Response.status(200).entity("hello 223 ").build();
    }
    // post - u log upisi 'pozvo si me'
}
```
#### main.webapp folder

* if not there, create main.webapp.WEB-INF folder and web.xml file

web.xml:
```ruby
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
	      http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         id ="WebApp_ID"
         version="3.0">
    <servlet>
        <servlet-name>demo-servlet</servlet-name>
        <display-name>Demo Servlet</display-name>
        <servlet-class>
            org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet
        </servlet-class>
        <init-param>
            <param-name>jaxrs.serviceClasses</param-name>
            <param-value>
                demo.rest.HelloRestService
            </param-value>
        </init-param>
        <init-param>
            <param-name>jaxrs.providers</param-name>
            <param-value>
                demo.rest.HelloRestService
            </param-value>
        </init-param>

        <!-- registers extension mappings -->
        <init-param>
            <param-name>jaxrs.extensions</param-name>
            <param-value>
                xml=application/xml
                json=application/json
            </param-value>
        </init-param>

        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>demo-servlet</servlet-name>
        <!--Prefix for endpoint-->
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
</web-app>
```

#### project folder

* find pom.xml

pom.xml:
```ruby
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <groupId>demo</groupId>
    <artifactId>DemoStudenti</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/javax.ws.rs/javax.ws.rs-api -->
        <dependency>
            <groupId>org.apache.cxf</groupId>
            <artifactId>cxf-rt-frontend-jaxrs</artifactId>
            <version>3.5.2</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.8.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <!--Indicate that its a web app-->
    <packaging>war</packaging>

</project>
```

### 4. Code run

* on right side intelij editor find 'Maven' tab
* expand lifecycle folder
* click on install
* click on clean
* click on compile
* click on package
* click on green play button next to the server
* click on given link (something like: http://localhost:8080/ProjectName)
* in your browser add on link '/hello' (http://localhost:8080/ProjectName/hello)
* there should be message 'hello 223'
* if you look in tomcat log at the bottom of InteliJ, there is message 'ovdje nesto pisi 223'

### 5. make changes

* After changing something in code, click in maven lifecycle 'package'
* then click on 'rerun tomcat' icon (that icon is on the same place where is green play button)
* refresh page

### 6. Postman 

* download postman (desktop version)
* install postman
* open postman
* in the middle of the screen in input field (between GET and Send) write URL from browser
* write "http://localhost:8080/ProjectName/hello" and click Send
* output should be 'hello 223'