package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.services;

import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.dto.PostDTO;

import java.util.List;

//we should not create bean for interfaces(don't use any service, or any other annotation etc.)
public interface PostService {

    List<PostDTO> getAllPost();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}
