package com.revature.controllers;

import java.util.List;

import com.revature.models.User;
import com.revature.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

     @GetMapping
     public List<User> findAll() {
         return userService.findAll();
     }

     @GetMapping("/{user_id}")
     public User findById(@PathVariable("user_id") int uId) {
        return userService.findById(uId);
     }

    
    }