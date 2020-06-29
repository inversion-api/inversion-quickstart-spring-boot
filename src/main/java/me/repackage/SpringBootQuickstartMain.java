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

import io.inversion.spring.EnableInversion;
import io.inversion.utils.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Launches an Inversion Spring Boot app via <code>SpringApplication#run(SpringBootQuickstartMain.class,args)</code>.
 * <p>
 * You can launch a fully running demo api with running the following gradle OR docker commands:
 * <b>Gradle:</b>
 * <pre>
 * gradle build
 * java -Ddb.class=io.inversion.jdbc.JdbcDb -Ddb.url=jdbc\:h2\:mem\:northwind\;DB_CLOSE_DELAY\=\-1 -Ddb.user=sa -Ddb.pass= -Ddb.ddlUrls=io\/inversion\/jdbc\/northwind\-h2.ddl -jar build/libs/inversion-quickstart-spring-boot.jar
 * </pre>
 * <b>Docker:</b>
 * <pre>
 * docker build . -t inversion-quickstart-spring-boot
 * docker run -p 8080:8080 -e db.class=io.inversion.jdbc.JdbcDb -e db.url=jdbc\:h2\:mem\:northwind\;DB_CLOSE_DELAY\=\-1 -e db.user=sa -e db.pass= -e db.ddlUrls=io\/inversion\/jdbc\/northwind\-h2.ddl inversion-quickstart-spring-boot
 * </pre>
 * <p>
 * After launching your api, take it for a spin by browsing to one of the following urls:
 * <ul>
 *  <li>http://localhost:8080/northwind/categories
 *  <li>http://localhost:8080/northwind/orders
 *  <li>http://localhost:8080/northwind/orderdetails
 *  <li>http://localhost:8080/northwind/products
 *  <li>http://localhost:8080/northwind/mock1
 *  <li>http://localhost:8080/northwind/custom1
 * <p>
 * If the code in this packages is unmodified, users must supply configuration for at least one <code>Db</code>
 * which can be anywhere <code>Config</code> can find it.  In this mode, Inversion will be doing all of the
 * component wiring and dependency injection based on the bean name.property pairs it finds in the Config.
 * This is called "full wiring mode."
 * <p>
 * Alternatively, if you prefer to write code to configure your api and have Spring do all of the DI, all you have
 * to do is supply factory methods for your components in an <code>@Configuration</code> annotated class.
 * <code>OptionalSpringConfig</code> is supplied as an example.  It is currently all commented out to not
 * conflict with the Config wiring model that runs out of the box.  With Spring doing the wiring, the Configurator
 * runs in "dependency injection mode".  Inversion will not fully wire up your api (you have made that Spring's job)
 * but it will still set bean name.property pair values it finds any in the Config.
 *
 * @see OptionalSpringConfig
 * @see io.inversion.utils.Config
 * @see io.inversion.utils.Configurator
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableInversion
public class SpringBootQuickstartMain {

    public static void main(String[] args) {

        String pass = Config.getString("db.pass");
        if (pass == null)
            pass = "UNDEFINED";

        if (pass != null)
            pass = pass.replaceAll(".", "*");

        System.out.println("  - db.class  = " + Config.getString("db.class"));
        System.out.println("  - db.driver = " + Config.getString("db.driver"));
        System.out.println("  - db.url    = " + Config.getString("db.url"));
        System.out.println("  - db.user   = " + Config.getString("db.user"));
        System.out.println("  - db.pass   = " + pass);

        SpringApplication.run(SpringBootQuickstartMain.class, args);
    }
}
