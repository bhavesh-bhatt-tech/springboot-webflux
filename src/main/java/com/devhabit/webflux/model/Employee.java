/**
 * 
 */
package com.devhabit.webflux.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employee")
public class Employee {

	@Id	
	private Integer id;
	@Column(value = "departmentId")
	private Integer departmentId;
	@Column(value = "firstName")
	private String firstName;
	@Column(value="lastName")
	private String lastName;
	@Column(value="dateOfBirth")
	private String dateOfBirth;
	
}
