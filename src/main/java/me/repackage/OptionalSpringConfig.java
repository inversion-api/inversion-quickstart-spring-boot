/*
 * Copyright (c) 2015-2020 Rocket Partners, LLC
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

import org.springframework.context.annotation.Configuration;

/**
 * If you want to have Spring do the bean wiring for your api instead of having Inversion do the wiring
 * based on bean name.property pairs, simply supply your own factory methods just like you would with
 * any other Spring wired app.
 * <p>
 * This file is not meant to be a master class in great Spring design but this should get you started.
 */
@Configuration
public class OptionalSpringConfig {
//    @Bean
//    public static Api buildMockApi1() {
//        return new Api()//
//                .withName("secondApi")//
//                .withEndpoint("GET", "*", new MockAction().withJson(new JSNode("hello_world", "from mock api one")));
//    }
//
//    @Bean
//    public static Api buildMockApi2() {
//        return new Api()//
//                .withName("thirdApi")//
//                .withEndpoint("GET", "*", new MockAction().withJson(new JSNode("hello_world", "from mock api two")));
//    }
//
//    /**
//     * Constructs a REST API that exposes database tables and REST collections.
//     */
//    @Bean
//    public static Api buildApi() {
//        return new Api().withName("northwind")
//                //
//                //-- if you give your api a name or an apiCode, it will become a required part of your url path structure...
//                //-- http://host[:port]/[Engine.servletMapping]/[Api.apiCode]/[Endpoint.path]/[collectionKey]/[entityKey]/[subCollecitonKey]
//                //--
//                //-- In this case we are not using a servlet mapping and the api name/code "northwind" null so
//                //-- we will be able to browse our db tables and custom actions here:
//                //--
//                //--  - http://localhost:8080/northwind/categories
//                //--  - http://localhost:8080/northwind/orders
//                //--  - http://localhost:8080/northwind/orderdetails
//                //--  - http://localhost:8080/northwind/products
//                //--  - http://localhost:8080/northwind/mock1
//                //--  - http://localhost:8080/northwind/custom1
//
//                //-- DATABASE CONFIGURATION OPTION #1.
//                //-- you can set your database connection information explicitly in the code here...
//                .withDb(new JdbcDb(
//                        //-- this is the 'name' you give your db connection. It is used to optionally set
//                        //-- db configuration via prop key/value in methods 2 & 3 below
//                        "myDb", //
//                        "org.h2.Driver", //-- replace with your jdbc driver class
//                        "jdbc:h2:mem:northwind;DB_CLOSE_DELAY=-1", //-- replace with your jdbc connection string
//                        "sa", //-- replace with your db username
//                        "", //-- replace with your db password
//                        //-- This last arg is an optional ddl file that is run each time the
//                        //-- engine starts.  Here it bootstraps the full db for demo purposes.
//                        //-- You can supply our own ddl file or simply remove this arg
//                        JdbcDb.class.getResource("northwind-h2.ddl").toString()))
//
//                //-- DATABASE CONFIGURATION OPTION #2 & #3
//                //-- comment out the above  "withDb()" method and uncomment below
//                //.withDb(new SqlDb("myDb"))
//
//                //-- then add the following name value pairs to one of the following
//                //--   - to an 'inversion.properties' file in the classpath
//                //--   - OR as java system properties
//                //--   - OR as environment variables
//                //--
//                //--  myDb.driver=${YOUR_JDBC_DRIVER}
//                //--  myDb.url=${YOUR_JDBC_URL}
//                //--  myDb.user=${YOUR_JDBC_USERNAME}
//                //--  myDb.pass=${YOUR_JDBC_PASSWORD
//
//                .withEndpoint(new Endpoint("GET,POST,PUT,DELETE", "/*", new DbAction()).withExcludeOn("*", "mock1/*,custom1/*"))//
//                .withEndpoint("GET", "mock1/*", new MockAction().withJson(new JSNode("hello_world", "from northwind api")))//
//                .withEndpoint("GET", "custom1/*", new Action() {
//                    @Override
//                    public void doGet(Request req, Response res) {
//                        res.data().add("action 1 value");
//                    }
//                }, new Action() {
//                    @Override
//                    public void doGet(Request req, Response res) {
//                        res.data().add("action 2 value");
//                    }
//                });
//
//    }
}
