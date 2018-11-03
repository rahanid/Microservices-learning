package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com")
@EnableTransactionManagement
@EnableDiscoveryClient
public class AppTwoApplication {
	
	@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }


	public static void main(String[] args) {
		SpringApplication.run(AppTwoApplication.class, args);
	}
	
	

	 
	
	
}
