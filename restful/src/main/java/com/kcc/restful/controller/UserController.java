package com.kcc.restful.controller;

import com.kcc.restful.UserDaoService;
import com.kcc.restful.bean.User;
import com.kcc.restful.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private UserDaoService userDaoService;


    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    // get Users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    // get User
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = userDaoService.findById(id);
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
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        // add location(ex. "http://localhost:8081/users/4") value in header
        return ResponseEntity.created(location).build();
    }

    // delete user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
       User user =  userDaoService.deleteById(id);

       if(user == null){
           throw new UserNotFoundException(String.format("User with id %s not found", id));
       }
    }

}
