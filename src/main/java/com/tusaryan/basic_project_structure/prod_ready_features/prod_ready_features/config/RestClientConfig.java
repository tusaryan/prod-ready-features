package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//creating an object of rest client
@Configuration
public class RestClientConfig {


    //to inject the field value from application.properties
    @Value("${employeeService.base.url}")
    private String BASE_URL;

    @Bean
    //if we have multiple rest clients then we can define unique identifiers using @Qualifier to access this particular bean
    @Qualifier
    RestClient getEmployeeServiceRestClient() {

        //using builder pattern to return this rest client and create its object
        return RestClient.builder()
                //we should not hardcode base url, we can use application property to pass in this variable/url
                .baseUrl(BASE_URL)
                //import CONTENT_TYPE from http headers, APPLICATION_JSON_VALUE from media type.
                //this will tell our RestApi that the return of your application should be in JSON only.
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                //to handle the error globally, instead of writing in test cases individually
                //from now on any time we are using this RestClient and if we get 5xx error than that will be handled here only
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (res, req) -> {
                    //we can also log the error here
                    throw new RuntimeException("Server error occured");
                })
                .build();
    }
}
