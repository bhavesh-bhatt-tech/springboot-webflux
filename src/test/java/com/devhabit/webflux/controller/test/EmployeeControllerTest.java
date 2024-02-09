/**
 * 
 */
package com.devhabit.webflux.controller.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.devhabit.webflux.controller.EmployeeController;
import com.devhabit.webflux.repository.EmployeeRepository;
import com.devhabit.webflux.service.EmployeeService;

/**
 * 
 */
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

	
	private static final Logger log = LoggerFactory.getLogger(EmployeeControllerTest.class);
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	private EmployeeController employeeController;
	
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
	 * Test method for {@link com.devhabit.webflux.controller.EmployeeController#findAllEmployees()}.
	 * @throws Exception 
	 */
	@Test
	void testFindAllEmployees() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/all")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test method for {@link com.devhabit.webflux.controller.EmployeeController#addEmployee(com.devhabit.webflux.model.Employee)}.
	 * @throws Exception 
	 */
	@Test
	void testAddEmployee() throws Exception {
		log.debug("testAddEmployee method start");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/employee/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"departmentId\" : 1,\r\n"
						+ "    \"firstName\" : \"Dilip\",\r\n"
						+ "    \"lastName\" : \"Patel\",\r\n"
						+ "    \"dateOfBirth\" : \"04-Jan-1972\"\r\n"
						+ "}")).andExpect(MockMvcResultMatchers.status().isOk());
		
		log.debug("testAddEmployee method end");
	}

	/**
	 * Test method for {@link com.devhabit.webflux.controller.EmployeeController#updateEmployee(com.devhabit.webflux.model.Employee)}.
	 * @throws Exception 
	 */
	@Test
	void testUpdateEmployee() throws Exception {
		log.debug("testUpdateEmployee method start");
		
		mockMvc.perform(MockMvcRequestBuilders.put("/employee/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"id\" : 1,\r\n"						
						+ "    \"departmentId\" : 1,\r\n"
						+ "    \"firstName\" : \"Dilip\",\r\n"
						+ "    \"lastName\" : \"Pathak\",\r\n"
						+ "    \"dateOfBirth\" : \"04-Jan-1972\"\r\n"
						+ "}")).andExpect(MockMvcResultMatchers.status().isOk());
		
		log.debug("testUpdateEmployee method end");
	}

	/**
	 * Test method for {@link com.devhabit.webflux.controller.EmployeeController#deleteEmployee(java.lang.Integer)}.
	 * @throws Exception 
	 */
	@Test
	void testDeleteEmployee() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.put("/employee/delete/{id}",1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
