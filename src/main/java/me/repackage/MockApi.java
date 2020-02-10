package me.repackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.inversion.cloud.action.misc.MockAction;
import io.inversion.cloud.model.Api;
import io.inversion.cloud.model.JSNode;

@Configuration
public class MockApi
{
   @Bean
   public static Api buildMockApi1()
   {
      return new Api()//
                      .withName("secondApi")//
                      .withEndpoint("GET", "*", new MockAction().withJson(new JSNode("hello_world", "from mock api one")));
   }

   @Bean
   public static Api buildMockApi2()
   {
      return new Api()//
                      .withName("thirdApi")//
                      .withEndpoint("GET", "*", new MockAction().withJson(new JSNode("hello_world", "from mock api two")));
   }

}
