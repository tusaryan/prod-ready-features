package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.repositories;

import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository<Type of Repository, type of id>
@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}