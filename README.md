# Inversion Cloud API Engine - Spring Boot Quick Start Guide

[![](https://travis-ci.org/inversion-api/inversion-quickstart-spring-boot.svg?branch=master)](https://travis-ci.org/inversion-api/inversion-quickstart-spring-boot)

Inversion is the fastest way to deliver full featured and secure REST APIs.

See https://github.com/inversion-api/inversion-engine for full documentation.

## Quick Start Instructions

- Have Docker installed on your system or otherwise know how to work with Java 8+ and Gradle projects.
- [Use this repo as a template](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-from-a-template) or otherwise clone/fork it etc.
- Repackage and modify "me.repackage.MySpringBootApiMain.java" to fit your needs.
- If you need additional DB drivers or other libs, add your custom dependencies to build.gradle.
- Edit Spring Boot properties in 'src/main/resources/application.properties' as needed.
- Edit logging settings in 'src/main/resources/logback.xml' as needed.
- To build and run via the Docker two stage build, run:
   - "docker build . -t quickstart"
   - "docker run -p 8080:8080 quickstart"
           
As long as you only have one class with a main() in it, the Spring Boot gradle plugin will detect it and use that as 
your jar main class.  That means you can rename and repackage MySpringBootApiMain.java into whatever you would like
and you don't need to modify the Dockerfile or build.gradle.

Finally, we would love your feedback, bug reports, and best of all PRs!

Thanks & enjoy, from the Inversion team @ [Rocket Partners](http://rocketpartners.io)