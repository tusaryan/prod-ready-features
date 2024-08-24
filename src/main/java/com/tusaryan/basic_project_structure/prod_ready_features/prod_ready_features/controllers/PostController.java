package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.controllers;

import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
//this will create a constructor that will inject the postService.
@RequiredArgsConstructor
public class PostController {

    //Also add pagination and sorting feature later.


    //Benefit of creating an interface.
    //Note: To use service code we are using the interface and not directly the implementation.
    //This improves our code Modularity and extensibility. It is a good practice to atleast add interface and then its implementation for our services.
    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        //to get all posts using postService
        return postService.getAllPost();
    }

    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable Long postId) {
        //to create abstract getPostById method in Post Service interface and then create that method to the interface implemented class -> option+return(mac) or alt+enter(windows) and create the method in interface and then in implemented class.
        return postService.getPostById(postId);
    }

    //getting an inputPost from request body of type PostDTO
    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        //we've not added any validation in PostDTO. Try that yourself.
        return postService.createNewPost(inputPost);
    }
}
