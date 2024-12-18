package com.aos.aosTP1.userService.service;

import com.aos.aosTP1.userService.JwtTokenProvider;
import com.aos.aosTP1.userService.model.User;
import com.aos.aosTP1.userService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public User registerUser(String username, String password) {
        
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

       
        String encodedPassword = passwordEncoder.encode(password);


        User newUser = new User(username, encodedPassword);
        return userRepository.save(newUser);
    }


    public String authenticateUser(String username, String password) {

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Invalid username or password!");
        }

        User user = optionalUser.get();


        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password!");
        }


        return jwtTokenProvider.generateToken(username);
    }
}