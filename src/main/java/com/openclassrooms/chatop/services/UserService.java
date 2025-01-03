package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
   }

   public boolean isUserExist(String email) {
        return this.userRepository.findByEmail(email) != null;
   }

   public User createUser(User user) {
       user.setPassword(this.hashPassword(user.getPassword()));
       user.setCreatedAt(Instant.now());
       user.setUpdatedAt(Instant.now());

       return user;
   }

    public String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
