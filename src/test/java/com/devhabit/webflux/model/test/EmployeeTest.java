/**
 * 
 */
package com.devhabit.webflux.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.devhabit.webflux.model.Employee;

/**
 * 
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeTest {
	
	@Mock
	private Employee mockEmp;
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
	 * Test method for {@link com.devhabit.webflux.model.Employee#getId()}.
	 */
	@Test
	void testGetId() {
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		assertEquals(1,employee.getId());
	}

	/**
	 * Test method for {@link com.devhabit.webflux.model.Employee#getDepartmentId()}.
	 */
	@Test
	void testGetDepartmentId() {
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		assertEquals(1,employee.getId());
	}

	/**
	 * Test method for {@link com.devhabit.webflux.model.Employee#getFirstName()}.
	 */
	@Test
	void testGetFirstName() {
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		assertEquals("Niraj",employee.getFirstName());
	}

	/**
	 * Test method for {@link com.devhabit.webflux.model.Employee#getLastName()}.
	 */
	@Test
	void testGetLastName() {
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		assertEquals("Sharma",employee.getLastName());
	}

	/**
	 * Test method for {@link com.devhabit.webflux.model.Employee#getDateOfBirth()}.
	 */
	@Test
	void testGetDateOfBirth() {
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		assertEquals("10-Mar-1987",employee.getDateOfBirth());

	}

	/**
	 * Test method for {@link com.devhabit.webflux.model.Employee#setId(Integer)}.
	 */
	@Test
	void testSetId() {
		
		
		doNothing().when(mockEmp).setId(1);
		mockEmp.setId(1);
		Mockito.when(mockEmp.getId()).thenReturn(1);
		
		assertEquals(1,mockEmp.getId());
	}

	/**
	 * Test method for {@link com.devhabit.webflux.model.Employee#setDepartmentId(Integer)}.
	 */
	@Test
	void testSetDepartmentId() {
		
		doNothing().when(mockEmp).setDepartmentId(1);
		mockEmp.setDepartmentId(1);
		Mockito.when(mockEmp.getDepartmentId()).thenReturn(1);
		
		assertEquals(1,mockEmp.getDepartmentId());
	}

	/**
	 * Test method for {@link com.devhabit.webflux.model.Employee#setFirstName(String)}.
	 */
	@Test
	void testSetFirstName() {
		
		doNothing().when(mockEmp).setFirstName("Akash");
		mockEmp.setFirstName("Akash");
		Mockito.when(mockEmp.getFirstName()).thenReturn("Akash");
		
		assertEquals("Akash",mockEmp.getFirstName());
	}

	/**
	 * Test method for {@link com.devhabit.webflux.model.Employee#setLastName(String)}.
	 */
	@Test
	void testSetLastName() {
		doNothing().when(mockEmp).setLastName("Mehta");
		mockEmp.setLastName("Mehta");
		Mockito.when(mockEmp.getLastName()).thenReturn("Mehta");
		
		assertEquals("Mehta",mockEmp.getLastName());
		
	}

	/**
	 * Test method for {@link com.devhabit.webflux.model.Employee#setDateOfBirth(String)}.
	 */
	@Test
	void testSetDateOfBirth() {
		doNothing().when(mockEmp).setDateOfBirth("10-March-1987");
		mockEmp.setDateOfBirth("10-March-1987");
		Mockito.when(mockEmp.getDateOfBirth()).thenReturn("10-March-1987");
		
		assertEquals("10-March-1987",mockEmp.getDateOfBirth());
	}
	/**
	 * Test method for {@link java.lang.Object#hashCode()}.
	 */
	@Test
	void testHashCode() {
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		Employee employee2 = new Employee(1,1,"Keval","Sharma","10-Mar-1987");

		Assert.assertNotEquals(employee.hashCode(),employee2.hashCode());
		Assert.assertEquals(employee.hashCode(),employee.hashCode());
		
	}

	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
	@Test
	void testEquals() {
		Employee employee = new Employee(1,1,"Niraj","Sharma","10-Mar-1987");
		Employee employee2 = new Employee(2,1,"Vishwa","Natak","10-Mar-1987");
		Employee employee3 = employee2;
		
		Assert.assertNotEquals(employee,employee3);
		Assert.assertEquals(employee2,employee3);		
		Assert.assertNotEquals(employee,employee2);
		Assert.assertNotEquals(employee.getFirstName(),employee2.getFirstName());
		Assert.assertEquals(employee.getDepartmentId(),employee2.getDepartmentId());
		Assert.assertNotEquals(employee.getLastName(),employee2.getLastName());
		Assert.assertEquals(employee.getDateOfBirth(),employee2.getDateOfBirth());
		
	}
}
