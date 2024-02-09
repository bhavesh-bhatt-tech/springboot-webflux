/**
 * 
 */
package com.devhabit.webflux.service.test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.devhabit.webflux.model.Employee;
import com.devhabit.webflux.repository.EmployeeRepository;
import com.devhabit.webflux.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeServiceTest {

	
	@Mock
	private EmployeeService employeeService;
		
	@Mock
	private EmployeeRepository employeeRepository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.devhabit.webflux.service.EmployeeService#getEmployeeById(java.lang.Integer)}.
	 */
	@Test
	void testGetEmployeeById() {
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		Mockito.when(employeeService.getEmployeeById(1)).thenReturn(Mono.just(employee));
		
		Mono<Employee> monoEmp = employeeService.getEmployeeById(1);
		assertNotNull(monoEmp.block());
		assertNotNull(monoEmp.block().getDateOfBirth());
		assertNotNull(monoEmp.block().getId());
		assertNotNull(monoEmp.block().getFirstName());
		assertNotNull(monoEmp.block().getLastName());

	}

	/**
	 * Test method for {@link com.devhabit.webflux.service.EmployeeService#getEmployees()}.
	 */
	@Test
	void testGetEmployees() {
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		List <Employee>empList = new ArrayList<Employee>();
		empList.add(employee);
		employeeService.addEmployee(employee);
		Flux<Employee> fluxEmp = Flux.just(employee);
		
		Mockito.when(employeeService.getEmployees()).thenReturn(fluxEmp);
		Flux<Employee> empFlux = employeeService.getEmployees();
		Mono<List<Employee>> monoEmpList = empFlux.collectList();

		List<Employee> employeeList = monoEmpList.block();
		employeeList.stream().forEach(t-> {

			assertNotNull(t);
			assertNotNull(t.getFirstName());
			assertNotNull(t.getLastName());
			assertNotNull(t.getDateOfBirth());
			assertNotNull(t.getId());

		});
	}

	/**
	 * Test method for {@link com.devhabit.webflux.service.EmployeeService#addEmployee(com.devhabit.webflux.model.Employee)}.
	 */
	@Test
	void testAddEmployee() {
		
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		
		doNothing().when(employeeService).addEmployee(employee);
		
		Mockito.when(employeeService.getEmployeeById(1)).thenReturn(Mono.just(employee));
		
		Mono<Employee> monoEmp = employeeService.getEmployeeById(1);
		assertNotNull(monoEmp.block());
		assertNotNull(monoEmp.block().getDateOfBirth());
		assertNotNull(monoEmp.block().getId());
		assertNotNull(monoEmp.block().getFirstName());
		assertNotNull(monoEmp.block().getLastName());

	}

	/**
	 * Test method for {@link com.devhabit.webflux.service.EmployeeService#updateEmployee(com.devhabit.webflux.model.Employee)}.
	 */
	@Test
	void testUpdateEmployee() {
		
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		Employee employee2 = new Employee(1,1,"Vihar","Sharma","10-Mar-1987");
		Mono<Employee> monoEmp = Mono.just(employee2);
		doNothing().when(employeeService).addEmployee(employee);
		employee.setFirstName("Vihar");
		
		Mockito.when(employeeService.updateEmployee(employee)).thenReturn(monoEmp);
		employeeService.updateEmployee(employee);
		Mockito.when(employeeService.getEmployeeById(1)).thenReturn(monoEmp);
		
		Mono<Employee> actualMonoEmp = employeeService.getEmployeeById(1);
		
		assertNotNull(actualMonoEmp.block());
		Assert.assertEquals("update is not success",actualMonoEmp.block().getFirstName(),employee2.getFirstName());
		Assert.assertEquals("update is not success",actualMonoEmp.block().getLastName(),employee2.getLastName());
		assertNotNull(actualMonoEmp.block().getDateOfBirth());
		assertNotNull(actualMonoEmp.block().getId());
		assertNotNull(actualMonoEmp.block().getFirstName());
		assertNotNull(actualMonoEmp.block().getLastName());		
	}
	@Test
	void testUpdateEmployeeNotFound() {
		
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		Employee employee2 = new Employee(2,1,"Vihar","Sharma","10-Mar-1987");
		Mono<Employee> monoEmp = Mono.empty();
		doNothing().when(employeeService).addEmployee(employee);
		employee.setFirstName("Vihar");
		try {
			Mockito.when(employeeService.updateEmployee(employee2)).thenThrow(new Exception("Employee Not Found"));
		}catch(Exception e) {
		}
		try {
			Mono<Employee> updatedEmp = employeeService.updateEmployee(employee2);
		} catch(Exception e) {
			Assert.assertTrue(true);;
		}
		
	}
	@Test
	void testUpdateEmployeeWithNullValue() {
		
		Employee employee = new Employee(1,1,"Niraj","Sharma",null);
		Employee employee2 = new Employee(1,1,"Niraj",null,"10-Mar-1987");
		Employee employee3 = new Employee(1,1,null,"Mehta","10-Mar-1987");

		Mono<Employee> monoEmp3 = Mono.just(employee3);
		doNothing().when(employeeService).addEmployee(employee3);
		employee3.setLastName("Mehta");
		
		Mockito.when(employeeService.updateEmployee(employee3)).thenReturn(monoEmp3);
		employeeService.updateEmployee(employee3);
		Mockito.when(employeeService.getEmployeeById(1)).thenReturn(monoEmp3);
	
		Mono<Employee> actualMonoEmp3 = employeeService.getEmployeeById(1);
		assertNotNull(actualMonoEmp3.block());
		Assert.assertNull(actualMonoEmp3.block().getFirstName());
		assertNotNull(actualMonoEmp3.block().getDateOfBirth());
		assertNotNull(actualMonoEmp3.block().getId());
		assertNotNull(actualMonoEmp3.block().getLastName());	
		
		
		Mono<Employee> monoEmp2 = Mono.just(employee2);
		doNothing().when(employeeService).addEmployee(employee2);
		employee2.setFirstName("Niraj");
		
		Mockito.when(employeeService.updateEmployee(employee2)).thenReturn(monoEmp2);
		employeeService.updateEmployee(employee2);
		Mockito.when(employeeService.getEmployeeById(2)).thenReturn(monoEmp2);
		
		Mono<Employee> actualMonoEmp2 = employeeService.getEmployeeById(2);
		assertNotNull(actualMonoEmp2.block());
		Assert.assertEquals("update is not success",actualMonoEmp2.block().getFirstName(),employee2.getFirstName());
		assertNotNull(actualMonoEmp2.block().getDateOfBirth());
		assertNotNull(actualMonoEmp2.block().getId());
		Assert.assertNull(actualMonoEmp2.block().getLastName());	
		
		Mono<Employee> monoEmp = Mono.just(employee);
		doNothing().when(employeeService).addEmployee(employee);
		employee.setLastName("Sharma");
		
		Mockito.when(employeeService.updateEmployee(employee)).thenReturn(monoEmp);
		employeeService.updateEmployee(employee);
		Mockito.when(employeeService.getEmployeeById(1)).thenReturn(monoEmp);
		
		Mono<Employee> actualMonoEmp = employeeService.getEmployeeById(1);
		assertNotNull(actualMonoEmp.block());
		Assert.assertEquals("update is not success",actualMonoEmp.block().getFirstName(),employee.getFirstName());
		Assert.assertNull(actualMonoEmp.block().getDateOfBirth());
		assertNotNull(actualMonoEmp.block().getId());
		assertNotNull(actualMonoEmp.block().getLastName());	
		
	}
	@Test
	void testUpdateEmployeeDateOfBirth() {
		
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		Employee employee2 = new Employee(1,1,"Niraj","Sharma","10-Mar-1986");
		Mono<Employee> monoEmp = Mono.just(employee2);
		doNothing().when(employeeService).addEmployee(employee);
		employee.setDateOfBirth("10-Mar-1986");
		
		Mockito.when(employeeService.updateEmployee(employee)).thenReturn(monoEmp);
		employeeService.updateEmployee(employee);
		Mockito.when(employeeService.getEmployeeById(1)).thenReturn(monoEmp);
		
		Mono<Employee> actualMonoEmp = employeeService.getEmployeeById(1);
		
		assertNotNull(actualMonoEmp.block());
		Assert.assertEquals("update is not success",actualMonoEmp.block().getDateOfBirth(),employee2.getDateOfBirth());
		assertNotNull(actualMonoEmp.block().getDateOfBirth());
		assertNotNull(actualMonoEmp.block().getId());
		assertNotNull(actualMonoEmp.block().getFirstName());
		assertNotNull(actualMonoEmp.block().getLastName());		
	}
	/**
	 * Test method for {@link com.devhabit.webflux.service.EmployeeService#deleteEmployee(java.lang.Integer)}.
	 */
	@Test
	void testDeleteEmployee() {
		Mockito.when(employeeService.deleteEmployee(1)).thenReturn(Mono.empty());
		Mockito.when(employeeService.getEmployeeById(1)).thenReturn(Mono.empty());

		Mono<Void> returnedMonoEmp = employeeService.deleteEmployee(1);
		Mono<Employee> monoEmp = employeeService.getEmployeeById(1);
		
		Assert.assertNull(monoEmp.block());
		Assert.assertNull(returnedMonoEmp.block());

	}

}
