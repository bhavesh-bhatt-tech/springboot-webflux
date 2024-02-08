/**
 * 
 */
package com.devhabit.webflux.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.devhabit.webflux.model.Employee;
import com.devhabit.webflux.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 */
@Service
public class EmployeeService {

	
	EmployeeRepository employeeRepository;
	
	EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	public Mono<Employee> getEmployeeById(Integer id) {
		return employeeRepository.findById(id);
	}

	public Flux<Employee> getEmployees() {
		return employeeRepository.findAll().delayElements(Duration.ofSeconds(2));
	}

	public void addEmployee(Employee employee) {
		
			employeeRepository.save(employee).subscribe();
	}

	public Mono<Employee> updateEmployee(Employee employee) {
		return employeeRepository.findById(employee.getId())
				.switchIfEmpty(Mono.error(new Exception("Employee Not Found"))).map(olderEmployee -> {
					if (employee.getFirstName() != null)
						olderEmployee.setFirstName(employee.getFirstName());
					if (employee.getLastName() != null)
						olderEmployee.setLastName(employee.getLastName());
					if (employee.getDateOfBirth() != null)
						olderEmployee.setDateOfBirth(employee.getDateOfBirth());
					if (employee.getDepartmentId() != null)
					  olderEmployee.setDepartmentId(employee.getDepartmentId());
					 					
					return olderEmployee;
				}).flatMap(employeeRepository::save);
	}

	public Mono<Void> deleteEmployee(Integer id) {
		return employeeRepository.deleteById(id);
	}
}
