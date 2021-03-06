package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient 
@RestController

public class MyAppApplication {
	
	
	public static final Logger LOGGER = LoggerFactory.getLogger(MyAppApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(MyAppApplication.class, args);
	}
	
	
	
	 @GetMapping("/check")
	    public String greeting() {
		 LOGGER.info("my app is up");
	        return "my app is up";
	    }
	 
	
	 
	 
	
}
