package org.dxc.springdatajpa.controller;

import java.util.List;
import java.util.Optional;

import org.dxc.springdatajpa.entity.Employee;
import org.dxc.springdatajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	@RequestMapping("/findall")
	public List<Employee> findAllEmployee(){
		return (List<Employee>)employeeRepository.findAll();
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public HttpStatus insertEmployee(@RequestBody Employee employee) {
		boolean status=employeeRepository.save(employee)!=null;
		return  status ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}
	@RequestMapping("/findbyid")
	public Optional<Employee> findById(@RequestParam("id")long id){
		Optional<Employee> result=employeeRepository.findById(id);
		return result;
	}
	
	@RequestMapping("/findbylastname")
	public List<Employee> fetchDataByName(@RequestParam("lastname")String lastName){
		return employeeRepository.findByLastName(lastName);
	}
	@RequestMapping("/findbyage")
	public List<Employee> fetchDataByAge(@RequestParam("age")int age){
		return employeeRepository.findByAge(age);
	}
	
}
