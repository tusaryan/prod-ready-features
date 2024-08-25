package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.config;

import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.auth.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//config package contains config classes like, configs of mapper to convert from Entity to DTO.
@Configuration
//enabling jpa auditing and then pass the auditor aware reference bean name (i.e. getAuditorAwareImpl).
//now jpa is also attached to the auditor and its value i.e. username is ("Aryan Kumar")
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl")
public class AppConfig {

    //Just a function that will return instance of model mapper
    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    AuditorAware<String> getAuditorAwareImpl() {
        return new AuditorAwareImpl();
    }
}
