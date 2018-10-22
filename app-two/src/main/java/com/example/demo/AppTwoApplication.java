package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin2.ZipkinProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import zipkin2.Span;

@SpringBootApplication
@RestController
public class AppTwoApplication {
	
	@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	
	@Autowired
	private EurekaClient eurekaClient;
	  
	@Autowired
	RestTemplate restTemplate;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AppTwoApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(AppTwoApplication.class, args);
	}
	
	

	 @GetMapping("/service2")
	    public String callService() {
		 LOGGER.debug("app 2 is up");
	        return "app 2 is up";
	    }
	 
	 @GetMapping("/callapp1")
	    public String callApp1() {
		 LOGGER.debug("app 2 calls my app");
		 LOGGER.info("app 2 calls my app");

	        Application application = eurekaClient.getApplication("my-app");
	        InstanceInfo instanceInfo = application.getInstances().get(0);
	        String url = "http://"+instanceInfo.getHostName()+":"+instanceInfo.getPort()+"/check";
		 ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
	        return resp.getBody();
	    }
	 
	
}
