package com.example.demo;

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
	  
	/*@Autowired
	private SpanMetricReporter spanMetricReporter;
	  
	@Autowired
	private ZipkinProperties zipkinProperties;
	  
	@Value("${spring.sleuth.web.skipPattern}")
	private String skipPattern;*/
	
	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(AppTwoApplication.class, args);
	}
	
	 @GetMapping("/service2")
	    public String callService() {
	        return "app 2 is up";
	    }
	 
	 @GetMapping("/callapp1")
	    public String callApp1() {
	        Application application = eurekaClient.getApplication("my-app");
	        InstanceInfo instanceInfo = application.getInstances().get(0);
	        String url = "http://"+instanceInfo.getHostName()+":"+instanceInfo.getPort()+"/check";
		 ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
	        return resp.getBody();
	    }
	 
	/* @Bean
	 public ZipkinSpanReporter makeZipkinSpanReporter() {
	     return new ZipkinSpanReporter() {
	    	 
	         private HttpZipkinSpanReporter delegate;
	         private String baseUrl;
	  
	         @Override
	         public void report(Span span) {
	   
	             InstanceInfo instance = eurekaClient.getNextServerFromEureka("zipkin", false);
	             if (!(baseUrl != null &&  instance.getHomePageUrl().equals(baseUrl))) {
	                 baseUrl = instance.getHomePageUrl();
	                 delegate = new HttpZipkinSpanReporter(baseUrl,zipkinProperties.getFlushInterval(), 
	                		 zipkinProperties.getCompression().isEnabled(),spanMetricReporter);
	   
	                 if (!span.name.matches(skipPattern)) 
	                	 delegate.report(span);
	             }
	         }
	     };
	 }*/
}
