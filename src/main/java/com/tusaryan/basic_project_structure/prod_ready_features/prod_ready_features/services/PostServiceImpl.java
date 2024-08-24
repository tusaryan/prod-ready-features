package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.services;

import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.entities.PostEntity;
import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@RequiredArgsConstructor -> add the constructor with all the required arguments.
@Service @RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    //Injecting the model mapper dependency. No need to define any constructor as Lombok annotation @RequiredArgsConstructor will manage it.
    private final ModelMapper modelMapper;

    //either we can pass the input as postRepository into the constructor by adding the constructor, or we can ask Lombok to do on our behalf using @RequiredArgsConstructor.
    /** public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }*/

    @Override
    public List<PostDTO> getAllPost() {
        //to return a list of post DTO, we need a model mapper(add model mapper dependency in pom.xml) that can convert a DTO to normal entity class.
        //adding a config for model mapper in config package.
        //fetching PostDTO
        //getting a list of post entities using findAll method from postRepo. Since our return type is List of PostDTO
        //So, we'll map it using stream. getting/mapping all postEntity to PostDTO.class using modelmapper. Then we 'convert to'/'collect it as' List using "collect" method.
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        //creating an entity from this dto, using modelmapper to map inputPost to PostEntity.class
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        //using Saving this post entity inside DB and use modelmapper to map/convert PostEntity with PostDTO and return PostDTO.
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        //using post repository to get PostEntity
        PostEntity postEntity = postRepository
                //find post by id if found than return it
                .findById(postId)
                //or else throw ResourceNotFound exception
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id : " + postId));
        //converting this postEntity to PostDTO class with the help of model mapper
        return modelMapper.map(postEntity, PostDTO.class);
    }
}
