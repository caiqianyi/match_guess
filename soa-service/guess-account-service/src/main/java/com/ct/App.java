package com.ct;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;

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
public class App extends SpringBootServletInitializer{
	
	 /**
     * 修改DispatcherServlet默认配置
     *
     * @param dispatcherServlet
     * @return
     * @author cqy
     * @create  2017年7月26日
     */
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        registration.getUrlMappings().clear();
        registration.addUrlMappings("*.json");
        return registration;
    }
	
	 public static void main(String[] args) {
		 System.out.println("以jar包方式启动...");
         SpringApplication.run(App.class, args);
     }
	 
	 @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.out.println("以war包方式启动...");
        return builder.sources(this.getClass());
     }
}
 