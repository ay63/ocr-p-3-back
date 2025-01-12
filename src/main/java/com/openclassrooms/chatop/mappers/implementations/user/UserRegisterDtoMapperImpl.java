package com.openclassrooms.chatop.mappers.implementations.user;

import com.openclassrooms.chatop.dto.user.UserRegisterDto;

import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.mappers.implementations.DtoMapper;
import com.openclassrooms.chatop.services.PasswordService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserRegisterDtoMapperImpl implements DtoMapper<User, UserRegisterDto> {

    private final PasswordService passwordService;

    UserRegisterDtoMapperImpl(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Override
    public User toEntity(UserRegisterDto dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordService.hashPassword(dto.getPassword()));
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        return user;
    }

    @Override
    public UserRegisterDto toDto(User user) {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
