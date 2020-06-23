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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import io.inversion.spring.EnableInversion;
import io.inversion.utils.Config;

/**
 * Quick start template/demo to wire up and run a REST API via Spring Boot wiring / dependency injection.
 * 
 * @see io.inversion.utils.Config
 * @see io.inversion.utils.Configurator
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableInversion
public class SpringBootQuickstartMain
{
   public static void main(String[] args)
   {
      System.out.println("  - db.class  = " + Config.getString("db.class"));
      System.out.println("  - db.driver = " + Config.getString("db.driver"));
      System.out.println("  - db.url    = " + Config.getString("db.url"));
      System.out.println("  - db.user   = " + Config.getString("db.user"));

      SpringApplication.run(SpringBootQuickstartMain.class, args);
   }
}
