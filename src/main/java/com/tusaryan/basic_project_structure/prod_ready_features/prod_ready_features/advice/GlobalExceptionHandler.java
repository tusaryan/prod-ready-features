package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.advice;

import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//to handle exceptions gracefully (i.e. Exception Handling in Spring MVC)
//this is the annotation that looks for all the exceptions and then handles the exception according to the Exception class mentioned in @ExceptionHandler("exception class")
@RestControllerAdvice
public class GlobalExceptionHandler {
    //to handle rest controller advice

    //handling all the exceptions globally that are occurring due to ResourceNotFoundException.
    //creating exception handler on resource not found exception class
    @ExceptionHandler(ResourceNotFoundException.class)
    //this method will return ResponseEntity of type ApiError
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception) {
        //creating an object of ApiError (for this we can use builder pattern or call the constructor) and then convert it to ResponseEntity
        //passing the message(can use .getLocalizedMessage() or .getMessage()) and error status code to the constructor
        ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        //encapsulating it inside ResponseEntity
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}