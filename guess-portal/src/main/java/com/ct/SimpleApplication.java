package com.ct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ct.soa.web.framework.datasource.DynamicDataSourceRegister;

//��ע���������
@EnableTransactionManagement
@Configuration  
@EnableAutoConfiguration
@ComponentScan  
@Import({DynamicDataSourceRegister.class})
@SpringBootApplication
public class SimpleApplication extends SpringBootServletInitializer{
	 public static void main(String[] args) {
       SpringApplication.run(SimpleApplication.class, args);
   }
	 
	 @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	   System.out.println("以war包方式启动...");
       // TODO Auto-generated method stub
       return builder.sources(SimpleApplication.class);
   }
	 	
}
