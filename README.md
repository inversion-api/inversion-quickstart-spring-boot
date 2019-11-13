# Inversion Cloud API Engine - Quick Start Guide

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
        
        
## Related Inversion Projects Build Status

| Project | Latest Build | Test Status |
|---|---|---| 
| [Inversion Engine](https://github.com/inversion-api/inversion-engine) | [![](https://jitpack.io/v/inversion-api/inversion-engine.svg)](https://jitpack.io/#inversion-api/inversion-engine)  | [![](https://travis-ci.org/inversion-api/inversion-engine.svg?branch=master)](https://travis-ci.org/inversion-api/inversion-engine) | 
| [Inversion Quick Start](https://github.com/inversion-api/inversion-quick-start) | [![](https://jitpack.io/v/inversion-api/inversion-quick-start.svg)](https://jitpack.io/#inversion-api/inversion-quick-start)  | [![](https://travis-ci.org/inversion-api/inversion-quick-start.svg?branch=master)](https://travis-ci.org/inversion-api/inversion-quick-start) |
| [Inversion Starter for DynamoDB](https://github.com/inversion-api/inversion-starter-dynamodb) | [![](https://jitpack.io/v/inversion-api/inversion-starter-dynamodb.svg)](https://jitpack.io/#inversion-api/inversion-starter-dynamodb)  | [![](https://travis-ci.org/inversion-api/inversion-starter-dynamodb.svg?branch=master)](https://travis-ci.org/inversion-api/inversion-starter-dynamodb) |
| [Inversion Starter for Elasticsearch](https://github.com/inversion-api/inversion-starter-elasticsearch) | [![](https://jitpack.io/v/inversion-api/inversion-starter-elasticsearch.svg)](https://jitpack.io/#inversion-api/inversion-starter-elasticsearch)  | [![](https://travis-ci.org/inversion-api/inversion-starter-elasticsearch.svg?branch=master)](https://travis-ci.org/inversion-api/inversion-starter-elasticsearch) |
| [Inversion Starter for Kinesis](https://github.com/inversion-api/inversion-starter-kinesis) | [![](https://jitpack.io/v/inversion-api/inversion-starter-kinesis.svg)](https://jitpack.io/#inversion-api/inversion-starter-kinesis)  | [![](https://travis-ci.org/inversion-api/inversion-starter-kinesis.svg?branch=master)](https://travis-ci.org/inversion-api/inversion-starter-kinesis) |
| [Inversion Starter for Redis](https://github.com/inversion-api/inversion-starter-redis) | [![](https://jitpack.io/v/inversion-api/inversion-starter-redis.svg)](https://jitpack.io/#inversion-api/inversion-starter-redis)  | [![](https://travis-ci.org/inversion-api/inversion-starter-redis.svg?branch=master)](https://travis-ci.org/inversion-api/inversion-starter-redis) |
| [Inversion Starter for S3](https://github.com/inversion-api/inversion-starter-s3) | [![](https://jitpack.io/v/inversion-api/inversion-starter-s3.svg)](https://jitpack.io/#inversion-api/inversion-starter-s3)  | [![](https://travis-ci.org/inversion-api/inversion-starter-s3.svg?branch=master)](https://travis-ci.org/inversion-api/inversion-starter-s3) |
| [Inversion Starter for Script](https://github.com/inversion-api/inversion-starter-script) | [![](https://jitpack.io/v/inversion-api/inversion-starter-script.svg)](https://jitpack.io/#inversion-api/inversion-starter-script)  | [![](https://travis-ci.org/inversion-api/inversion-starter-script.svg?branch=master)](https://travis-ci.org/inversion-api/inversion-starter-script) |
| [Inversion Starter for Spring Boot](https://github.com/inversion-api/inversion-starter-spring-boot) | [![](https://jitpack.io/v/inversion-api/inversion-starter-spring-boot.svg)](https://jitpack.io/#inversion-api/inversion-starter-spring-boot)  | [![](https://travis-ci.org/inversion-api/inversion-starter-spring-boot.svg?branch=master)](https://travis-ci.org/inversion-api/inversion-starter-spring-boot) |



## Gradle Cheat Sheet

 - 'gradle eclipse' generates your Eclipse project files and conveniently sets up the customary project folder structure.
 - 'gradle printVersion' shows the SermVer version of your project computed by git-versioner Gradle plugin.
 - 'gradle tagVersion' pushes version tags to GitHub.  If you get a credential related error, just run 'git push --tags' to finish the job.
 
