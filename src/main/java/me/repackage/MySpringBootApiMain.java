/*
 * Copyright (c) 2015-2018 Rocket Partners, LLC
 * https://github.com/inversion-api
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.repackage;

import io.inversion.cloud.action.misc.MockAction;
import io.inversion.cloud.action.rest.RestAction;
import io.inversion.cloud.action.sql.SqlDb;
import io.inversion.cloud.model.Action;
import io.inversion.cloud.model.Api;
import io.inversion.cloud.model.Endpoint;
import io.inversion.cloud.model.JSNode;
import io.inversion.cloud.model.Request;
import io.inversion.cloud.model.Response;
import io.inversion.cloud.service.Chain;
import io.inversion.cloud.service.Engine;
import io.inversion.cloud.service.spring.InversionApp;

/**
 * Quick start example to wire up and run a REST API  
 * that exposes relational db tables as REST collections.
 * 
 * See https://github.com/inversion-api/inversion-engine for full configuration options.
 *      
 */
public class MySpringBootApiMain
{

   public static void main(String[] args) throws Exception
   {
      InversionApp.run(buildApi());
   }

   /**
    * Constructs a REST API that exposes database tables and REST collections. 
    */
   public static Api buildApi()
   {
      return new Api()
                      //.withName("northwind")
                      //
                      //-- if you give your api a name or an apiCode, it will become a required part of your url path structure...
                      //-- http://host[:port]/[Engine.servletMapping]/[Api.apiCode]/[Endpoint.path]/[collectionKey]/[entityKey]/[subCollecitonKey]
                      //-- 
                      //-- In this case we are not using a servlet mapping and the api name/code is null so 
                      //-- we will be able to browse our db tables and custom actions without those path components
                      //-- 
                      //--  - http://localhost:8080/categories
                      //--  - http://localhost:8080/orders
                      //--  - http://localhost:8080/orderdetails
                      //--  - http://localhost:8080/products
                      //--  - http://localhost:8080/mock1
                      //--  - http://localhost:8080/custom1

                      //-- DATABASE CONFIGURATION OPTION #1.
                      //-- you can set your database connection information explicitly in the code here... 
                      .withDb(new SqlDb(
                                        //-- this is the 'name' you give your db connection. It is used to optionally set 
                                        //-- db configuration via prop key/value in methods 2 & 3 below
                                        "myDb", //
                                        "org.h2.Driver", //-- replace with your jdbc driver class
                                        "jdbc:h2:mem:northwind;DB_CLOSE_DELAY=-1", //-- replace with your jdbc connection string
                                        "sa", //-- replace with your db username
                                        "", //-- replace with your db password
                                        //-- This last arg is an optional ddl file that is run each time the  
                                        //-- engine starts.  Here it bootstraps the full db for demo purposes.
                                        //-- You can supply our own ddl file or simply remove this arg
                                        SqlDb.class.getResource("northwind-h2.ddl").toString()))

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

                      .withEndpoint(new Endpoint("GET,PUT,POST,DELETE", "/*", new RestAction()).withExcludePaths("mock1/*,custom1/*"))//
                      .withEndpoint("GET", "mock1/*", new MockAction().withJson(new JSNode("hello", "world")))//
                      .withEndpoint("GET", "custom1/*", new Action()
                         {
                            @Override
                            public void doGet(Engine engine, Api api, Endpoint endpoint, Chain chain, Request req, Response res) throws Exception
                            {
                               res.data().add("action 1 value");
                            }
                         }, new Action()
                            {
                               @Override
                               public void doGet(Engine engine, Api api, Endpoint endpoint, Chain chain, Request req, Response res) throws Exception
                               {
                                  res.data().add("action 2 value");
                               }
                            });

   }

}
