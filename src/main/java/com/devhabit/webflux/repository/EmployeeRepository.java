/**
 * 
 */
package com.devhabit.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.devhabit.webflux.model.Employee;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer> {

}
