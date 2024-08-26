package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name = "posts")
//Hibernate also require these (@AllArgsConstructor, @NoArgsConstructor) constructors to create the Entities and convert tables to Entities and so on.
@AllArgsConstructor
@NoArgsConstructor
//to convert DTOs back to Entity and vice-versa (use @Data-> (for JPA entities this can cause severe performance and memory consumption issues). So use @Getter, @Setter)
@Getter
@Setter
//due to @Getter and @Setter now modelMapper can easily get all the value from DTO and can set the same value inside Entity as well.

//to make this entity as auditable, and it is of kind AuditingEntityListener.class , Also add @EnableJpaAuditing to configuration class to enable auditing.
//@EntityListeners(AuditingEntityListener.class)
@Audited
public class PostEntity extends AuditableEntity {

    //for now, we're ignoring the part to create base entity to make it auditable. we are using PostEntity to do that.
    //Since every entity/DB will have auditing so to make our code reusable(i.e. to avoid repeating the code) it's better to create a Base class to enable auditing.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // to exclude some field from being audited
    // @NotAudited
    private String description;

    /**
    //This @EntityListeners will automatically populate these fields on our behalf every time there is any creation or updation.
    //this automatically fill the created time in createdDate variable
    @CreatedDate
    //to enforce so that it is not null and can't be updated.
    //and actually it will not be updated by JPA as well
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    //to hold the username of person who created/modified that in createdBy/updatedBy variable/field.
    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;
     */


    //these are called lifecycle hooks for DB
    //make sure these annotations are coming from jakarta.persistence
    //we can also use these create/implement our own version of Auditing without using this @EntityListeners(AuditingEntityListener.class)

    //before saving this method will get triggered
    //useful when you want to make changes inside some other entity based on changes in above entity/field/variables (i.e. id, title, description)
    @PrePersist
    void beforeSave() {
        //example: Before saving the post entity, the user who created this post, I want to update the post count in that entity
        // so before saving this I can reference that particular entity and update the post count.
    }

    //this function will be triggered before update
    @PreUpdate
    void beforeUpdate() {

    }

    @PreRemove
    void beforeDelete() {

    }


}
