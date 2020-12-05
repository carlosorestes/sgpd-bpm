package com.sgpd.br.bpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sgpd.br.bpm")
@ComponentScan("com.springboot.camunda")
public class Application extends SpringBootServletInitializer{

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
  
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

}