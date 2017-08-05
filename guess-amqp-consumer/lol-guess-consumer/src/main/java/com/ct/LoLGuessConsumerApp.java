package com.ct;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.ct.soa.web.framework.datasource.DynamicDataSourceRegister;


@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan
@Import({DynamicDataSourceRegister.class}) //多数据源管理
@SpringBootApplication
@EnableRabbit
@EnableDiscoveryClient
@EnableFeignClients
public class LoLGuessConsumerApp{
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
        SpringApplication.run(LoLGuessConsumerApp.class, args);
    }
	
}
 