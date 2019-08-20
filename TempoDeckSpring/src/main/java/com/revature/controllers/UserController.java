package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import com.revature.dtos.UserConverted;
import com.revature.models.User;
import com.revature.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

     @GetMapping("/old")
     public List<User> findAll() {
         return userService.findAll();
     }

     @GetMapping
     public List<UserConverted> findAllConverted(){
         List<User> dbUsers = userService.findAll();
         List<UserConverted> users = new ArrayList<>();
         dbUsers.forEach(user -> {
             users.add(new UserConverted(
                 user.getId(), 
                 user.getUsername(), 
                 user.getFirstName(), 
                 user.getLastName(), 
                 user.getEmail(), 
                 user.getRole())
                 );
         });
         return users;
     }

     @GetMapping("/old/{id}")
     public User findById(@PathVariable("id") int id) {
        return userService.findById(id);
     }

    @GetMapping("/{id}")
    public UserConverted findByIdConverted(@PathVariable("id") int id) {
        User dbUser = userService.findById(id);
        return new UserConverted(
            dbUser.getId(), 
            dbUser.getUsername(), 
            dbUser.getFirstName(), 
            dbUser.getLastName(), 
            dbUser.getEmail(), 
            dbUser.getRole()
            );
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public UserConverted updateUser(@RequestBody UserConverted user) {
        User toConvert = userService.updateUser(user);
        return new UserConverted(
            toConvert.getId(), 
            toConvert.getUsername(), 
            toConvert.getFirstName(), 
            toConvert.getLastName(), 
            toConvert.getEmail(), 
            toConvert.getRole());
    }
}