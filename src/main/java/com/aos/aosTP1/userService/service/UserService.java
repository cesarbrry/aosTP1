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

    public User registerUser(User user) 
    {
        
        if (userRepository.findByUsername(user.getUsername()).isPresent()) 
        
        {
            throw new RuntimeException("Username already exists!");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        return userRepository.save(user);
    }


    public String authenticateUser(String username, String password) 
    {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid username or password!");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword()))
        {
            throw new RuntimeException("Invalid username or password!");
        }
        else
        {
            return jwtTokenProvider.generateToken(username);
        }
        
    }
}