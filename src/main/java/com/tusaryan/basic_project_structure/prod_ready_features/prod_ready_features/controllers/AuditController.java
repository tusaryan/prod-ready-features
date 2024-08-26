package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.controllers;

import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.entities.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//API for admins to track the auditing activity
@RestController
@RequestMapping(path = "/audit")
public class AuditController {

    //creating a entity manager to pass inside AuditReaderFactory
    @Autowired
    public EntityManagerFactory entityManagerFactory;


    //AuditReader -> we can read the revisions for a particular audit
    //since this controller is only for admins(because admins can see anything), so we can return the PostEntity instead of PostDTO (since controller should have PostDTO)
    //to track all the audit that were made to certain post id
    @GetMapping(path = "/posts/{postId}")
    List<PostEntity> getPostRevisions(@PathVariable Long postId) {
        //In order to get all the revision on a particular postId we have read through all the Revisions that were made inside our DB.

        // use this "reader" to through all the revisions for a particular Entity (it could be any entity - not specific to Post Entity)
        //this will return the entity manager
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());

        //to get revision for type PostEntity with this postId
        // these are revision numbers
        List<Number> revisions = reader.getRevisions(PostEntity.class, postId);


        //as a beginner you are not expected to remember all these. you can easily search it on internet.
        //getting the list of revisions using revision number
        //go through each revisionNumber then map revision number with reader then use reader to find the revision for (this class, post id, revision number) then convert it list
        return revisions
                .stream()
                .map(revisionNumber -> reader.find(PostEntity.class, postId, revisionNumber))
                .collect(Collectors.toList());
    }
}
