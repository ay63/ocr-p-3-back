package com.openclassrooms.chatop.services;

import org.springframework.stereotype.Service;


public interface PasswordService {

    String hashPassword(String rawPassword);

    boolean checkPassword(String rawPassword, String encodedPassword);

}
