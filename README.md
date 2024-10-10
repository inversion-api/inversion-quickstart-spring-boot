# Inversion Cloud API Engine - Spring Boot Quick Start Guide

Inversion is the fastest way to deliver full featured and secure REST APIs.

See https://inversion.io and https://github.com/inversion-api/inversion-engine for more extended documentation, guides, 
tutorials, and of course all the code.

## Contents
- [Prerequisites](#inversion-cloud-api-engine)
- [Quick Start](#quick-start)
- [Connecting to your own Database](#connecting-to-your-own-database)
- [Configuring your Api](#configuring-your-api)
- [Coding an Api with Spring Boot](#coding-an-api-with-spring-boot)
- [Using this Project as a Template](#using-this-project-as-a-template)

## Prerequisites

1. Inversion runs on all major operating systems and requires only a Java JDK or JRE version 17 or higher to be installed.
1. OR, if you don't have Java on your system you can also run Inversion on Docker with the supplied dockerfile if you prefer.
1. To checkout this code you will also need git installed.

## Quick Start

This demo launches a full featured API that exposes SQL database tables as REST collection endpoints.  The demo 
supports full GET,POST,PUT,DELETE operations with an extensive Resource Query Language (RQL) for GET requests.
 
The demo database is an in-memory H2 SQL database that gets initialized from scratch each time the demo runs.  That 
means you can fully explore modifying operations (POST,PUT,PATCH,DELETE) and 'break' whatever you want. When 
you restart that data will be back in its original demo state.

After checking out the demo api, you can easily supply your own database connection information to run an Api
that connects to your data.  

1. Use [this repo](https://github.com/inversion-api/inversion-quickstart-spring-boot) as a [template](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-from-a-template) or otherwise clone/fork it etc.
    ```shell script
    git clone https://github.com/inversion-api/inversion-quickstart-spring-boot.git
    cd inversion-quickstart-spring-boot
    ```

1. If you have Java 17+ installed, you can also build and run the demo via Gradle: 
    ```shell script
    ./gradlew build
    ./gradlew demo
    ```
   OR
   ```shell script
   ./gradlew build
   ./gradlew run -Ddb.class=io.inversion.jdbc.JdbcDb -Ddb.url=jdbc\:h2\:mem\:northwind\;DB_CLOSE_DELAY\=\-1 -Ddb.user=sa -Ddb.pass= -Ddb.ddlUrls=io\/inversion\/jdbc\/northwind\-h2.ddl
   ```
   OR
   ```shell script
   ./gradlew build
   java -Ddb.class=io.inversion.jdbc.JdbcDb -Ddb.url=jdbc\:h2\:mem\:northwind\;DB_CLOSE_DELAY\=\-1 -Ddb.user=sa -Ddb.pass= -Ddb.ddlUrls=io\/inversion\/jdbc\/northwind\-h2.ddl -jar build/libs/inversion-quickstart-spring-boot.jar
   ```

1. If you have Docker installed, you can launch the built in "northwind" demo api with the following commands:
    ```shell script
    docker build . -t inversion-quickstart-spring-boot
    docker run -p 8080:8080 -e db.class=io.inversion.jdbc.JdbcDb -e db.url=jdbc\:h2\:mem\:northwind\;DB_CLOSE_DELAY\=\-1 -e db.user=sa -e db.pass= -e db.ddlUrls=io\/inversion\/jdbc\/northwind\-h2.ddl inversion-quickstart-spring-boot
    ``` 
 

The demo API is now running at 'http://localhost:8080/ with REST collection endpoints for each DB table.

You can get started by exploring some of these urls:
 - GET http://localhost:8080/products
 - GET http://localhost:8080/orders?expands=orderDetails&page=2
 - GET http://localhost:8080/customers?in(country,France,Spain)&sort=-customerid&pageSize=10
 - GET http://localhost:8080/customers?orders.shipCity=Mannheim

Append '&explain=true' to any query string to see an explanation of what is happening under the covers
 - GET http://localhost:8080/employees?title='Sales%20Representative'&sort=employeeid&pageSize=2&page=2&explain=true

Checkout the [RQL Guide](https://github.com/inversion-api/inversion-engine#resource-query-language-rql) to see all of the 
advanced querying, filtering, and pagination options built into Inversion.

- If you want to use the simple configuration model:
- OR, if you are comfortable coding with Java and Spring Boot, you may want to use code to wire up your api.

## Connecting to your own Database

You can test drive an Api connected to yoru own dabase by swapping out the values of "db.url", "db.user", "db.pass"
in the above examples.  Inversion ship H2, MySql, Postgress, and MsSQLServer drivers.  If you want to connect
do a different JDBC compatible db, you will need to add the driver as a Gradle dependency and specify the classname
in a "db.driver" property.

## Configuring your Api

In addition to supplying configuration options as system props or environment variables as we saw above, Inversion supports
wiring up an Api based on simple "beanName.property=value" properties file syntax.  

This project comes with a place holder "/src/main/resources/inversion.properties" config file.  You can place
our "db.property=value" configuration in this file along with any other Inversion features you would like to wire
into your api.   

See the [Config](https://inversion-api.github.io/inversion-engine/javadoc/io/inversion/utils/Config.html) 
and [Configurator](https://inversion-api.github.io/inversion-engine/javadoc/io/inversion/utils/Configurator.html) JavaDoc 
for more details on how to where and how Inversion loads its configs.


## Coding an Api with Spring Boot

Some Java/Spring Boot developers may prefer to wire up their Apis in code instead of through configuration.  The project
includes a few examples in OptionalSpringConfig.java.  

When the Configurator detects that Apis have been "manually" wired
up in Spring, it will not instantiate "bean.class=className" beans defined in the configuration but it will still 
attempt to reflectively set beanName.property=value values.  This lets you use Spring Boot DI as much or as little as you 
like and also use Inversion's simple property runtime DI simultaneously.    


## Using this Project as a Template

The Gradle build and dockerfile included with this project are designed to make it easy for you to use this repo as a
[GitHub template](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-template-repository).

If you are going to build your Api with configuration not code, you can use everything almost exactly as it is.  Just
add your config one or more "inversion.properties" files and modify any Spring Boot settings in "application.properties" 
if necessary.

If you want to wire up your Api in Java/Spring Boot, you will want to take a look at OptionalSpringConfig.java as
an example and probably rename/repackage both OptionalSpringConfig.java and SpringBootQuickstartMain.java to fit
your needs.

Finally, check out the comments in the build.gradle to see all of the different ways you can include the Inversion
project dependencies in your own project.  
           

#### Finally, we would love your feedback, bug reports, and best of all PRs!

#### Thanks & enjoy, from the Inversion team @ [Rocket Partners](http://rocketpartners.io)