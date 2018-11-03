package com.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.dtos.Result;
import com.entities.Employee;
import com.repositories.EmployeeRepostiory;

import io.vavr.control.Try;

@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class EmployeeService {
	
	@Autowired
	EmployeeRepostiory empRepo;
	
	public Result<Employee> create(String name, String dept) {
		Result<Employee> result = null;
	Optional<Employee> optEmp = empRepo.findByNameAndDepartment( name, dept);
		if (optEmp.isPresent())
		return result = new Result(optEmp.get(),null);
		Try<Employee> retVal = Try.success(empRepo.save(new Employee(name,dept))).orElse(Try.failure(new RuntimeException("employee creation failed")));
		result = new Result(retVal.getOrNull(),retVal.isFailure()?retVal.getCause():null);
		return result;
	}
	
	public Result<Employee> update(Integer id , String name, String dept) {
		Optional<Employee> emp = empRepo.findById(id);
		if (!emp.isPresent())
			throw new RuntimeException("employee not found for the id");
		emp.get().setName(name);
		emp.get().setDepartment(dept);
		return new Result(empRepo.save(emp.get()),null);
	}
	
	public Boolean delete (Integer id){
		boolean exists = empRepo.existsById(id);
		if (exists)
			empRepo.deleteById(id);
		return exists;
	}

}
