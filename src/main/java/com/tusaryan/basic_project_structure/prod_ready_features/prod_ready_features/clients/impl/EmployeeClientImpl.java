package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.clients.impl;

import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.advice.ApiResponse;
import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


//to call this api we need to create controllers/create tests

@Service
//lombok annotation to create the constructors for all the arguments
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    //to handle any kind of error we can use 'RestClient. ... '."onStatus()"
    //all 4xx category errors are related to client side errors
    //all 5xx category errors are related to server side errors
    //Server/Connection not found exception : when the server is not up/ not running

//    private static final Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    //inject RestClient bean
    private final RestClient restClient;

    //creating instance of logger, import from org.slf4j
    //inside .getLogger(pass the name of the class that is instantiating this particular log)
    //why did we pass this class? so that our logger can specify the name of class correctly
    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);


    /**Writing Log for a production ready application
     * writing log in service file, because it contains the business logic
     * since it is very much prone to multiple types of errors, so it makes much more sense to write error logs here
     */


    @Override
    public List<EmployeeDTO> getAllEmployees() {

        //these are just the markers/tags for the logs they don't really mean anything
        /**
        log.error("error log");
        log.warn("warn log");
        log.info("info log");
        log.debug("debug log");
        log.trace("trace log");
         */
        //we can only see the top three logs
        //till info the logs are enabled by default
        //to see debug and trace log -> enable it by "setting Log levels" in application.properties for different packages
        //we can use log.trace instead of log.info etc. but these are the markers, and it's better to adhere to these markers.


        log.trace("Trying to retrieve all employees in getAllEmployees");

        //using the restClient to call the API
        //there could be a lot of error in this process. for eg: We're passing wrong data - Client Error(4XX Errors), Server Runtime error - server related error i.e. 500 error, if server not running then Connection error
        try {
            log.info("Attempting to call the restClient Method in getAllEmployees");
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    //this uri will be appended to the base url in RestClient
                    //using this url we get all the employees
                    .uri("employees")
                    //using retrieve method - to get all the data
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })

                    //"body" method - to convert the data to a particular type of object
                    //pass what kind of data we are expecting to be returned, List is a parametrized type object so pass this reference
                    //so, it will automatically detect and convert it to List of EmployeeDTO

                    .body(new ParameterizedTypeReference<>() {
                    })
                    //Suppose return type of method was EmployeeDTO or String then we can use below ".body"
//                    .body(EmployeeDTO.class)
                    ;
            //to specify some information about the application
            log.debug("Successfully retrieved the employees in getAllEmployees");

            //trace : this contains more detailed information about our debug.
            //to print all the employees, it recommended to use log.trace(message, pass data), we can use log.debug as well but use log.debug only to give detailed info about the application
            //use curly braces {} in order to pass in "employeeDTOList.getData()" or multiple variables
            log.trace("Retrieved employees list in getAllEmployees : {}, {}, {}", employeeDTOList.getData(), "Hello", 5);

            return employeeDTOList.getData();

        } catch (Exception e) {
            //we can also log the errors to see why it is throwing exception / to debug application.
//            log.error(Error log message, pass the error object so that it will automatically print the stack trace);

            //anytime we get an exception we should write an error log there.
            log.error("Exception occurred in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("Trying to get employee by Id in getEmployeeById with id: {}", employeeId);
        //it is recommended to use try catch block so that we can add logger statements
        try {
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    //passing the employeeId in "uri"
                    .uri("employees/{employeeId}", employeeId, "hello", 12.12)
                    //format of passing values
//                    .uri("employees/{employeeId}/{abc}/{xyz}", employeeId, "hello", 12.12)
                    .retrieve()
                    //to show client error
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not get employee by id");
                    })
                    .body(new ParameterizedTypeReference<>(){
                    });
            //this will return the employee with this "employeeId"
            return employeeResponse.getData();

        } catch (Exception e) {
            log.error("Exception occurred in getEmployeeById", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("Creating an employee with information {}", employeeDTO);
        try {
//            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient.post()
            //when we use toEntity() change return type to ResponseEntity
            //in ResponseEntity we body, header, status code, etc. so using toEntity we can access all of that
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    //request body, employeeDTO is the expected employee Body
                    .body(employeeDTO)
                    //instead of .retrieve we can do "exchange call" that will return both request and response
                    .retrieve()
                    //to handle any error. Pass the type of error
                    //get the request and response object
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.debug("4xx client error occurred during createNewEmployee");
                        //to log the error, this will read all the errors that are coming here
                        //currently we are printing all the error in the console which is not recommended, we should log that somewhere else(like in some file)
                        log.error(new String(res.getBody().readAllBytes()));
                        //to throw error
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    //We'll get the Response Body in return
                    //convert the Response Body to Parameterized type reference
//                    .body(new ParameterizedTypeReference<>() {
//                    })
                    //instead of "body" we can use .toEntity and get Entity and instead of "ApiResponse<EmployeeDTO>" we will return "ResponseEntity<ApiResponse<EmployeeDTO>>"
                    .toEntity(new ParameterizedTypeReference<>() {
                    })
                    ;

            //to get headers if we use ResponseEntity
//            employeeDTOApiResponse.getHeaders();


            //return the newly created employee
//            return employeeDTOApiResponse.getData();

            //if we use ResponseEntity
            log.trace("Successfully created a new employee : {}", employeeDTOApiResponse.getBody());
            return employeeDTOApiResponse.getBody().getData();
        }
        //since we handle 4xx and 5xx error earlier so now exception other than that will be handled here like (Connection Error/Server down)
        catch (Exception e) {
            log.error("Exception occurred in createNewEmployee", e);
            throw new RuntimeException(e);
        }
    }
}
