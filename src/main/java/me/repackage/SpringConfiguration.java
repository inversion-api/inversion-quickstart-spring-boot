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

import io.inversion.*;
import io.inversion.action.misc.MockAction;
import io.inversion.jdbc.JdbcDb;
import io.inversion.utils.JSNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * If you want to have Spring do the bean wiring for your api instead of having Inversion do the wiring
 * based on bean name.property pairs, simply supply your own @Bean methods and @Component classes just like
 * you would with any other Spring wired app.
 * <p>
 * This file is not meant to be a master class in great Spring design but this should get you started.
 */
@Configuration
public class SpringConfiguration {

    /**
     * If you don't need Spring to DI a bunch of custom dependencies into Action subclasses, the easies
     * way to get your API going is to simply wire it up in an @Bean annotated factory method.
     * <p>
     * After Spring wires up all of your beans, during Engine.startup(), the Inversion Configurator will
     * still set beanName.propertyName=value key pairs found in Spring Environment PropertySources
     * or in your inversion*.properties files.
     * <p>
     * This method relies on the availability of the following configuration that can be found either
     * in Spring's config or Inversion's config.  Currently as part of this example, these properties
     * can be found in application.properties.
     * <p>
     * <ol>
     *     <li>northwindDb.driver=org.h2.Driver</li>
     *     <li>northwindDb.url=jdbc:h2:mem:northwind;DB_CLOSE_DELAY=-1</li>
     *     <li>northwindDb.ddlUrls=io/inversion/jdbc/northwind-h2.ddl</li>
     *     <li>northwindDb.user=sa</li>
     *     <li>northwindDb.pass=</li>
     * </ol>
     * <p>
     * Checkout these urls to browse your "northwind" Api.
     * <ul>
     *     <ol>http://localhost:8080/northwind/categories</ol>
     *     <ol>http://localhost:8080/northwind/orders</ol>
     *     <ol>http://localhost:8080/northwind/orderdetails</ol>
     *     <ol>http://localhost:8080/northwind/products</ol>
     * </ul>
     *
     * @return an Api that exposes the Northwind DB as a set of Rest collections.
     */
    @Bean
    public Api buildNorthwindApi()
    {

        return new Api().withName("menu").withDb(new JdbcDb("northwindDb"));
    }

    @Bean
    public Api buildSimpleApi(){
        return new Api("simple").withEndpoint("GET", "greeting/*", new MockAction(new JSNode("greeting", "Hello World!")));
    }

    /**
     * Even when using Spring bean wiring, you can still declare an entire Api that will be wired up by the
     * Inversion Configurator AFTER Spring bean wiring has finished.
     * <p>
     * The below @Bean annotation is intentionally commented out so that Spring won't load the Status Api.
     * <p>
     * Instead, the properties configuration equivalent to the code below has been included in the application.properties file.
     * <p>
     * When you run the application, you will have a stats Api configured via properties, just as if you had
     * configured it in code.
     * <p>
     * Checkout these urls to browse your "status" Api.
     * <ul>
     *     <ol>http://localhost:8080/status</ol>
     * </ul>
     *
     * @return an Api with a single Endpoint that returns an empty 200 that can be used for cluster health checks
     */
    //@Bean -- intentionally commented out to give an example of how the Api defined in this method can be defined in the application.properties
    public Api buildStatusApi()
    {
        if(true)
            throw new RuntimeException("THIS METHOD IS HERE FOR EXAMPLE PURPOSES ONLY AND SHOULD NOT BE CALLED.");

        return new Api().withName("status").withEndpoint("*", "*", new MockAction());
    }


    /**
     * Creates a custom @Component Api subclass showing you how to use Spring DI with Inversion classes.
     * <p>
     * If you are not creating custom Action or Db subclasses that you want to DI with Spring,
     * you probably don't need to use this method and could simply use the wiring style seen in
     * {@code #buildStatusApi()} or {@code #buildStatusApi()}
     * <p>
     * Make sure to remember that you can't necessarily do all of your own wiring in the constructor
     * because depending on your you setup your @Bean, @Component, @Value annotations etc,
     * all of your dependencies may not be available yet.  An @PostConstruct annotated method is the best
     * place to finish your setup.
     * <p>
     * Checkout these urls to browse your "custom" Api.
     * <ul>
     *     <ol>http://localhost:8080/custom/greeting</ol>
     * </ul>
     *
     * @return a custom Spring DI'd Api
     */
    @Component
    public static class CustomApi extends Api
    {
        @Value( "${customApi.apiName}" )
        String apiName = null;

        @Value( "${customApi.methods}" )
        String methods = null;

        @Value( "${customApi.paths}" )
        String paths = null;

        /**
         * Will be constructor injected by Spring
         */
        CustomAction customAction = null;

        public CustomApi(CustomAction action) {
            this.customAction = action;
        }

        @PostConstruct
        private void postConstruct()
        {
            withName(apiName);
            withEndpoint(methods, paths, customAction);
        }
    }

    /**
     * Simple example of a custom Action @Component.
     *
     * If you are building custom Action classes to handle your own specific
     * business logic needs, you may want to have Spring DI your Action's
     * dependencies.
     */
    @Component
    public static class CustomAction extends Action<CustomAction>
    {
        @Value( "${customAction.property1}" )
        String property1 = null;

        @Value( "${customAction.property2}" )
        String property2 = null;

        @Autowired
        CustomComponent customComponent = null;

        public void run(Request req, Response res) throws ApiException {

            res.getJson().put("property1", property1);
            res.getJson().put("property2", property2);
            res.getJson().put("greeting", customComponent.sayHello());
        }
    }

    /**
     * Simple example of a custom @Component that gets injected into an action.
     */
    @Component
    public static class CustomComponent
    {
        @Value( "${customComponent.greeting}" )
        String gretting = null;

        public String sayHello() {
            return gretting;
        }
    }
}
