package com.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.AppTwoApplication;
import com.dtos.Result;
import com.entities.Employee;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.services.EmployeeService;

import io.vavr.collection.List;
import io.vavr.control.Either;


@RestController
@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AppTwoApplication.class);
		
	
	@Autowired
	private EurekaClient eurekaClient;
	  
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	EmployeeService service;
	
	
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Employee> createEmployee(
			@RequestParam(required=true,value="name") String name, 
			@RequestParam(value="department") String dept) {
		return service.create(name, dept);
	}
	
	@PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Employee> editEmployee(
			@RequestParam(required=true,value="id") Integer id, 
			@RequestParam(required=true,value="name") String name, 
			@RequestParam(required=true,value="department") String dept) {
		return service.update(id,name, dept);
	}
	
	@DeleteMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean deleteEmployee(
			@RequestParam(required=true,value="id") Integer id, 
			@RequestParam(required=true,value="name") String name, 
			@RequestParam(required=true,value="department") String dept) {
		return service.delete(id);
	}
	

	 @GetMapping("/service2")
	    public String callService() {
		 LOGGER.info("app 2 is up");
	        return "app 2 is up";
	    }
	 
	 @GetMapping("/callapp1")
	    public String callApp1() {
		 LOGGER.info("app 2 calls my app");

	        Application application = eurekaClient.getApplication("my-app");
	        InstanceInfo instanceInfo = application.getInstances().get(0);
	        String url = "http://"+instanceInfo.getHostName()+":"+instanceInfo.getPort()+"/check";
		 ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
	        return resp.getBody();
	    }

	
	 @GetMapping(value="/employees",produces = MediaType.APPLICATION_JSON_VALUE)
	 public List<String> getEmployees(){	 
		 return (List<String>) new ArrayList<String>();
	 }
	 
	
}
