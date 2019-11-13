package io.inversion.cloud.quickstart;

import io.inversion.cloud.action.rest.RestAction;
import io.inversion.cloud.action.sql.SqlDb;
import io.inversion.cloud.model.Api;
import io.inversion.cloud.service.spring.InversionApp;

/**
 * Quick start example to wire up and run a REST API  
 * that exposes relational db tables as REST collections.
 * 
 * See https://github.com/inversion-api/inversion-engine for full configuration options.
 *      
 */
public class MyApiMain
{
   /**
    * Constructs a REST API that exposes database tables and REST collections. 
    */
   public static Api buildApi()
   {
      return new Api()
            .withName("demo")
            
            //-- DATABASE CONFIGURATION OPTION #1.
            //-- you can set your database connection information explicitly in the code here... 
            .withDb(new SqlDb("myDb", 
                              "${YOUR_JDBC_DRIVER}", 
                              "${YOUR_JDBC_URL}", 
                              "${YOUR_JDBC_USERNAME}", 
                              "${YOUR_JDBC_PASSWORD}"))
            
            //-- DATABASE CONFIGURATION OPTION #2 & #3
            //-- comment out the above  "withDb()" method and uncomment below
            //.withDb(new SqlDb("myDb"))
            
            //-- then add the following name value pairs to one of the following
            //--   - to an 'inversion.properties' file in the classpath
            //--   - OR as java system properties
            //--   - OR as environment variables
            //-- 
            //--  myDb.driver=${YOUR_JDBC_DRIVER}
            //--  myDb.url=${YOUR_JDBC_URL}
            //--  myDb.user=${YOUR_JDBC_USERNAME}
            //--  myDb.pass=${YOUR_JDBC_PASSWORD
            
            .withEndpoint("GET,PUT,POST,DELETE", "/*", new RestAction());
   }

   public static void main(String[] args) throws Exception
   {
      InversionApp.run(buildApi());
   }
}
