package me.repackage.wiredByInversion;

import io.inversion.spring.InversionMain;

/**
 * Quick start template/demo that wires up a REST API via Inversion's native properties file based
 * wiring /dependency injection and launches it in Tomcat via Spring Boot.
 * 
 * @see io.inversion.utils.Config
 * @see io.inversion.utils.Configurator
 */
public class WiredByInversionMain
{
   public static void main(String[] args)
   {
      System.setProperty("inversion.configPath",  "config/wiredByInversion");
      InversionMain.run();
   }
}
