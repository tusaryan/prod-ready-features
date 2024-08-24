package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

//advice package contains classes for global exception handling
//Custom Error -> basic class, will use it to throw the errors.
@Data
public class ApiError {

    //time stamp
    private LocalDateTime timeStamp;
    //message
    private String error;
    //status code
    private HttpStatus status;

    //default constructor
    public ApiError() {
        //set the current time
        this.timeStamp = LocalDateTime.now();
    }

    //constructor that can take Error and Status Code.
    public ApiError(String error, HttpStatus status) {

        /**
         * By using this(), the second constructor ensures that all member variables have valid values before proceeding with its own logic.
         * In this case, if timeStamp wasn't initialized in any other way within the second constructor, it would remain null by default
         * that could potentially lead to unexpected behavior or errors if code tries to access an uninitialized timeStamp.
         * */
        //here this() means calling the default constructor first
        this();
        this.error = error;
        this.status = status;
    }
}
