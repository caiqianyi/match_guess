package com.ct;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ct.soa.web.framework.datasource.DynamicDataSourceRegister;

@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan
@Import({DynamicDataSourceRegister.class}) //多数据源管理
@SpringBootApplication
@EnableRabbit
public class GuessJobApp extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(GuessJobApp.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		System.out.println("以war包方式启动...");
		return builder.sources(this.getClass());
	}

}
