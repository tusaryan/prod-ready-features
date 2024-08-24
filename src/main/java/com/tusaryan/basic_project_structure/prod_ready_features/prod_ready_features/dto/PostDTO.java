package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data ->used for getters and setters
@Data
//@AllArgsConstructor, @NoArgsConstructor -> reqd by Jackson to convert Json to this Class
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;
    //to add validation first add Validation dependency(i.e Spring data validator) in pom.xml
    //example title should be non-empty and its length etc.
    private String title;
    private String description;
}
