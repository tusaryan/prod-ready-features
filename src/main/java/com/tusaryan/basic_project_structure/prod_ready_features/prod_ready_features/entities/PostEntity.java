package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "posts")
//Hibernate also require these (@AllArgsConstructor, @NoArgsConstructor) constructors to create the Entities and convert tables to Entities and so on.
@AllArgsConstructor
@NoArgsConstructor
//to convert DTOs back to Entity and vice-versa (use @Data-> (for JPA entities this can cause severe performance and memory consumption issues). So use @Getter, @Setter)
@Getter
@Setter
//due to @Getter and @Setter now modelMapper can easily get all the value from DTO and can set the same value inside Entity as well.
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
}
