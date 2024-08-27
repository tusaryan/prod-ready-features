package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.clients;

import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    //creating an end point that will get all the clients that we have
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long employeeId);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}