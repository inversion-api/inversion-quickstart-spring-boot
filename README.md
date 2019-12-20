# Inversion Cloud API Engine - Spring Boot Quick Start Guide

[![](https://jitpack.io/v/inversion-api/inversion-quick-start.svg)](https://jitpack.io/#inversion-api/inversion-quick-start)  [![](https://travis-ci.org/inversion-api/inversion-quick-start.svg?branch=master)](https://travis-ci.org/inversion-api/inversion-quick-start)

Inversion is the fastest way to deliver full featured and secure REST APIs.

See https://github.com/inversion-api/inversion-engine for full documentation.

## Quick Start Instructions

- Have at least Java 8 installed on your system.
- Clone this git repo locally
- From the working directory run "./gradlew eclipse".
- Import the working directory as an Eclipse project
- Edit MyApiMain.java with your DB connection information
- Add any additional Endoints and Action you would like in MyApiMain.java
- If you need additional DB drivers or other libs, add your custom dependencies to build.gradle
- Now you can run MyApiMain from within Eclipse...
- OR run it a Docker container:
   - "docker build . -t quickstart"
   - "docker run -p 8080:8080 quickstart"
           
As long as you only have one class with a main() in it, the SpringBoot gradle plugin will detect it and use that as 
your jar main class.  That means you can rename and repackage MyApiMain.java into whatever you would like.

Edit Spring Boot properties in 'src/main/resources/application.properties' and log settings in 'src/main/resources/logback.xml' to suit your needs.
        
## Command Cheat Sheet

git tag -a 0.6.3 -m "Version 0.6.3"
git push --tags