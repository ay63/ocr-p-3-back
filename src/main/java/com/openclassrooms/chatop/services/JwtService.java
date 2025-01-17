package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.entities.User;
import org.springframework.stereotype.Service;


@Service
public interface JwtService {

    String generateToken(User user);
}
