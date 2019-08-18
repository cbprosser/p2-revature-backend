package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.dtos.Credentials;
import com.revature.dtos.UserConverted;
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
    public UserConverted findByUsernameAndPassword(@RequestBody Credentials cred) {
        User dbUser = userService.findByUsernameAndPassword(cred.getUsername(), cred.getPassword());
        return new UserConverted(
            dbUser.getUserId(),
            dbUser.getUsername(),
            dbUser.getFirstName(),
            dbUser.getLastName(),
            dbUser.getEmail(),
            dbUser.getRole().getName()
            );
    }

    @GetMapping("/check-auth")
    public User checkAuth(HttpServletRequest req) {
        return (User) req.getSession().getAttribute("user");
    }

}