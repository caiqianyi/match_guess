package com.ct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;


@Configuration  
@SpringBootApplication
public class App{
	
	 public static void main(String[] args) {
		 
         SpringApplication.run(App.class, args);
     }
}
 