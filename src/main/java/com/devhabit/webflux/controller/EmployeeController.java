/**
 * 
 */
package com.devhabit.webflux.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devhabit.webflux.model.Employee;
import com.devhabit.webflux.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	
	private final EmployeeService employeeService;

	
	/**
	 * @param employeeService
	 */
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK) 
	public Flux<Employee> findAllEmployees() {
  
	  return employeeService.getEmployees();
	}

	/**
	 * 
	 * @param updateEmployee
	 * @return
	 */
	@PostMapping(value="/add" ) 
	@ResponseStatus(HttpStatus.OK)
	public void addEmployee(@RequestBody Employee employee) {
		 employeeService.addEmployee(employee);
	}

	/**
	 * 
	 * @param employee
	 * @return
	 */
	@PutMapping(value="/update", produces = MediaType.APPLICATION_JSON_VALUE) 
	@ResponseStatus(HttpStatus.OK)
	public Mono<Employee> updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@PutMapping(value="/delete/{id}" ) 
	@ResponseStatus(HttpStatus.OK)
	public Mono<Void> deleteEmployee(@PathVariable Integer id) {
		return employeeService.deleteEmployee(id);
	}
	
}
