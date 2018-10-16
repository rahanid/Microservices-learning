package com.example.demo;

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
	
	/*@Autowired
	private RestTemplate restTemplate;*/

	public static void main(String[] args) {
		SpringApplication.run(MyAppApplication.class, args);
	}
	
	 @GetMapping("/check")
	    public String greeting() {
	        return "my app is up";
	    }
	 
	/* @GetMapping("/callAnotherService")
	    public String callService() {
	        return "my app is up";
	    }*/
	 
	 
	
}
