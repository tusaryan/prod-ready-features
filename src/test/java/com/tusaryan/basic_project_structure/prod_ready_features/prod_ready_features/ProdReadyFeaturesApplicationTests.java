package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features;

import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
//so that @Order annotation working correctly add @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeaturesApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;


	@Test
	//to change the order of test case running
	@Order(3)
	void getAllEmployees() {
		//using employeeClient to get all the employees
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
		//printing all the employees list
		System.out.println(employeeDTOList);
	}

	@Test
	@Order(2)
	void getEmployeeByIdTest() {
		//passing id 1 as 1L since it is of type Long
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}

	@Test
	@Order(1)
	void createNewEmployeeTest() {
		//creating a mock data to pass
		//creating an employee dto to pass it to client to create a new employee
		EmployeeDTO employeeDTO = new EmployeeDTO(null, "Aryan", "aryan@gmail.com", 2,
				"USER", 5000.0, LocalDate.of(2020, 12, 24), true);
		EmployeeDTO savedEmployeeDTO = employeeClient.createNewEmployee(employeeDTO);
		System.out.println(savedEmployeeDTO);
	}

}
