package com.ct;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ct.soa.web.framework.datasource.DynamicDataSourceRegister;


//启注解事务管理
@EnableTransactionManagement
@Configuration  
@EnableAutoConfiguration
@ComponentScan  
@Import({DynamicDataSourceRegister.class}) //多数据源管理
@SpringBootApplication
@EnableRabbit
@EnableDiscoveryClient
public class GuessLoLServiceApplication extends SpringBootServletInitializer{
	
	 public static void main(String[] args) {
         SpringApplication.run(GuessLoLServiceApplication.class, args);
     }
	 
	 @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
     }
}
 