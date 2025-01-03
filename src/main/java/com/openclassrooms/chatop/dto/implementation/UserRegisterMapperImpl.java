package com.openclassrooms.chatop.dto.implementation;

import com.openclassrooms.chatop.dto.UserRegisterDTO;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.dto.mappers.UserRegisterMapper;
import com.openclassrooms.chatop.services.PasswordService;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMapperImpl implements UserRegisterMapper {

    private final PasswordService passwordService;

    UserRegisterMapperImpl(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Override
    public User toEntity(UserRegisterDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordService.hashPassword(dto.getPassword()));
        return user;
    }

    @Override
    public UserRegisterDTO toDto(User user) {
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
