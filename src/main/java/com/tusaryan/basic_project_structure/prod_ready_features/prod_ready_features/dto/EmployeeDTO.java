package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//so that we can see everything clearly (i.e. no hash codes)
@ToString
public class EmployeeDTO {
    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String role;

    private Double salary;

    private LocalDate dateOfJoining;

    private Boolean isActive;

}
