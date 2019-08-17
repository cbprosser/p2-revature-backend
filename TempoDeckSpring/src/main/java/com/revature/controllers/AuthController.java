package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.models.User;
import com.revature.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public User findByUsernameAndPassword(@RequestBody User user) {

        return userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @GetMapping("/check-auth")
	public User checkAuth(HttpServletRequest req) {
		return (User) req.getSession().getAttribute("user");
	}

}