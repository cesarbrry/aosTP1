package com.aos.aosTP1.userService.controller;

import com.aos.aosTP1.userService.model.User;
import com.aos.aosTP1.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User userRequest) {
        User newUser = userService.registerUser(userRequest.getUsername(), userRequest.getPassword());
        return ResponseEntity.ok(newUser);  
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody User userRequest) {
        String token = userService.authenticateUser(userRequest.getUsername(), userRequest.getPassword());
        return ResponseEntity.ok(token); 
    }
}