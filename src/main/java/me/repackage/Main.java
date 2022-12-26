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

import io.inversion.Api;
import io.inversion.action.misc.MockAction;
import io.inversion.spring.config.EnableInversion;
import io.inversion.spring.config.InversionRegistrar;
import io.inversion.spring.main.InversionMain;
import io.inversion.utils.JSNode;
import io.inversion.utils.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Launches an Inversion Spring Boot app via <code>SpringApplication#run(SpringBootQuickstartMain.class,args)</code>.
 * <p>
 * You can launch a fully running demo api by running the following gradle OR docker commands:
 * <b>Gradle:</b>
 * <pre>
 * ./gradlew build
 * java -Ddb.class=io.inversion.jdbc.JdbcDb -Ddb.url=jdbc\:h2\:mem\:northwind\;DB_CLOSE_DELAY\=\-1 -Ddb.user=sa -Ddb.pass= -Ddb.ddlUrls=io\/inversion\/jdbc\/northwind\-h2.ddl -jar build/libs/inversion-quickstart-spring-boot.jar
 * </pre>
 * A super easy shortcut gradle task 'demo' has been provided so you can also simply run
 * <pre>
 * ./gradlew demo
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
 * <p>
 * If the code in this packages is unmodified, users must supply configuration for at least one <code>Db</code>
 * which can be anywhere <code>Config</code> can find it.  In this mode, Inversion will be doing all of the
 * component wiring and dependency injection based on the bean name.property pairs it finds in the Config.
 * This is called "full wiring mode."
 * <p>
 * Please check the docs on <code>Config</code> for more info but there are four simple ways to supply this config:
 * <ol>
 *     <li>as Java system properties as seen in the above examples</li>
 *     <li>ad environment variables</li>
 *     <li>in "inversion.properties" files located in src/main/resources/ folder</li>
 *     <li>in an ".env" file in the working directory.  See the supplied env.EXAMPLE for more info.
 * </ol>
 * <p>
 * Alternatively, if you prefer to write code to configure your api and have Spring do all of the DI, all you have
 * to do is supply factory methods for your components in an <code>@Configuration</code> annotated class.
 * <code>OptionalSpringConfig</code> is supplied as an example.  It is currently all commented out to not
 * conflict with the Config wiring model that runs out of the box.  With Spring doing the wiring, the Configurator
 * runs in "dependency injection mode".  Inversion will not fully wire up your api (you have made that Spring's job)
 * but it will still set bean name.property pair values it finds any in the Config.
 *
 * @see SpringConfiguration
 * @see io.inversion.utils.Config
 * @see io.inversion.utils.Configurator
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableInversion
public class Main {

    protected static ApplicationContext context = null;

    public static void main(String[] args) {
        run(args, null);
    }

    /**
     * Convenience method for launching a Engine with Api beans that were
     * configured outside of Spring Boot DI.
     *
     * @param apis the Apis to run
     * @return the SpringBoot ApplicationContext for the running server
     */
    public static ApplicationContext run(Api... apis) {
        return run(new String[]{}, apis);
    }

    public static ApplicationContext run(String[] args, Api[] apis) {
        try {

            if (context != null)
                exit();

            InversionRegistrar.apis = apis;
            context = SpringApplication.run(Main.class, args);
        } catch (Throwable e) {
            e = Utils.getCause(e);
            if (Utils.getStackTraceString(e).contains("A child container failed during start")) {
                String msg;
                msg = " README FOR HELP!!!!!!!";
                msg += "\n";
                msg += "\n It looks like you are getting a frustrating Tomcat startup error.";
                msg += "\n";
                msg += "\n This error may be casused if URL.setURLStreamHandlerFactory()";
                msg += "\n is somehow called before Spring Boot starts Tomcat. ";
                msg += "\n";
                msg += "\n This seems to be a frustrating undocumented \"no no\" of Tomcat with ";
                msg += "\n Spring Boot. Using H2 db before Spring Boot starts Tomcat seems to ";
                msg += "\n be one known cause of this error.";
                msg += "\n";
                msg += "\n SOLUTION: Override Engine.startup0() and place all of your Api wiring";
                msg += "\n and other setup code there.  That way Tomcat will load before ";
                msg += "\n the part of your code that is causing this unintended side effect.";
                msg += "\n\n\n";

                System.err.println(msg);
                throw new RuntimeException(msg, e);
            } else {
                e.printStackTrace();
            }
            Utils.rethrow(e);
        }

        return context;
    }

    public static void exit() {
        if (context != null)
            SpringApplication.exit(context);
        context = null;
    }

}
