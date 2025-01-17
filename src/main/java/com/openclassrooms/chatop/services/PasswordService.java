package com.openclassrooms.chatop.services;

public interface PasswordService {

    String hashPassword(String rawPassword);

    boolean checkPassword(String rawPassword, String encodedPassword);

}
