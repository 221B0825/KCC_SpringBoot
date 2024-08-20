package com.kcc.restful.controller;

import com.kcc.restful.UserDaoService;
import com.kcc.restful.bean.Post;
import com.kcc.restful.bean.User;
import com.kcc.restful.exception.UserNotFoundException;
import com.kcc.restful.repository.PostRepository;
import com.kcc.restful.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // get Users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    // get User
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }

        EntityModel entityModel = EntityModel.of(user);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        entityModel.add(linkTo.withRel("all-users"));

        return entityModel;
    }

    // create user
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        // add location(ex. "http://localhost:8081/users/4") value in header
        return ResponseEntity.created(location).build();
    }

    // delete user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrievePosts(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }

        return user.get().getPosts();
    }
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> addPost(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }
        post.setUser(user.get());
//        user.get().getPosts().add(post);
        postRepository.save(post);

        // URI
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts/{post_id}")
    public EntityModel<Post> retrievePost(@PathVariable int id, @PathVariable int post_id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }else {
            Optional<Post> post = postRepository.findById(post_id);
            if (!post.isPresent()) {
                // not found post exception
                throw new UserNotFoundException(String.format("Post with id %s not found", id));
            }else{
                if(post.get().getUser().equals(user.get())){
                    return EntityModel.of(post.get());
                }
                // not equal user_id
                throw new UserNotFoundException(String.format("Post with id %s not found", post_id));
            }
        }
    }

}
