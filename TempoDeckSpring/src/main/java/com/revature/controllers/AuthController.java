package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.dtos.Credentials;
import com.revature.dtos.UserConverted;
import com.revature.models.User;
import com.revature.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserConverted findByUsernameAndPassword(@RequestBody Credentials cred) {
        User dbUser = userService.findByUsernameAndPassword(cred.getUsername(), cred.getPassword());
        return new UserConverted(
            dbUser.getId(),
            dbUser.getUsername(),
            dbUser.getFirstName(),
            dbUser.getLastName(),
            dbUser.getEmail(),
            dbUser.getRole()
            );
    }

    @GetMapping("/check-auth")
    public UserConverted checkAuth(HttpServletRequest req) {
        User sessionUser = (User) req.getSession().getAttribute("user");
        if (sessionUser != null) {
            return new UserConverted(
                sessionUser.getId(), 
                sessionUser.getUsername(), 
                sessionUser.getFirstName(), 
                sessionUser.getLastName(), 
                sessionUser.getEmail(), 
                sessionUser.getRole());
        }
        return null;
    }

}