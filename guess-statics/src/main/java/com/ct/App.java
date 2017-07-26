package com.ct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;


@Configuration  
@SpringBootApplication
public class App{
	
	 public static void main(String[] args) {
		 
		 System.out.println("以jar包方式启动...");
         SpringApplication.run(App.class, args);
     }
	 
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.out.println("以war包方式启动...");
        return builder.sources(this.getClass());
     }
}
 