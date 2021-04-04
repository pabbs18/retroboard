package com.project.retroboard.controllers;

import com.project.retroboard.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create/user")
    public void createUser(@RequestParam(required = true) String username,
                             @RequestParam(required = true) String password,
                             @RequestParam(required = true) String role){
        userService.createUser(username, password,role);
    }
}
