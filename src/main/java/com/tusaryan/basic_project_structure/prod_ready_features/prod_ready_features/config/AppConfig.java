package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//config package contains config classes like, configs of mapper to convert from Entity to DTO.
@Configuration
public class AppConfig {

    //Just a function that will return instance of model mapper
    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
