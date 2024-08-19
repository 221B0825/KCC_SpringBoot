package com.kcc.restful.controller;

import com.kcc.restful.UserDaoService;
import com.kcc.restful.bean.User;
import com.kcc.restful.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService userDaoService;


    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    // get Users
    @GetMapping("/users")
    public List<User> retriveAllUsers(){
        return userDaoService.findAll();
    }

    // get User
    @GetMapping("/users/{id}")
    public User retriveUser(@PathVariable int id){
        User user = userDaoService.findById(id);
        if(user == null){
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }
        return user;
    }

    // create user
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
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
