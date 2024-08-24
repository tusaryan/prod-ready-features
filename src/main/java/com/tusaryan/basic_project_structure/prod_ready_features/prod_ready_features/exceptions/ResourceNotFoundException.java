package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    //constructor that takes the message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
