package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.repositories.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
   }

   public boolean isUserExist(String email) {
        return this.userRepository.findByEmail(email) != null;
   }
}
