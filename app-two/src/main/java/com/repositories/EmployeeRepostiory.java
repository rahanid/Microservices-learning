package com.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entities.Employee;

@Repository
public interface EmployeeRepostiory  extends JpaRepository<Employee, Integer>{
	
	public Optional<Employee> findByNameAndDepartment( String name, String department);

}
