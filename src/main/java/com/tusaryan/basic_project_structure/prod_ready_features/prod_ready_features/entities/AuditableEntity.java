package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//for this entity to be considered super class so that other classes can extend it.
//this will add all the field inside it to all tables that inherit from this entity.
@MappedSuperclass
@Getter
@Setter
//to make this entity as auditable, and it is of kind AuditingEntityListener.class , Also add @EnableJpaAuditing to configuration class to enable auditing.
@EntityListeners(AuditingEntityListener.class)
//now this is our base entity, and now this is listening for all the events
public class AuditableEntity {
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
}
