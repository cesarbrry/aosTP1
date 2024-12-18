package com.aos.aosTP1.userService.repository;

import com.aos.aosTP1.userService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Méthode pour trouver un utilisateur par son username
    Optional<User> findByUsername(String username);

}