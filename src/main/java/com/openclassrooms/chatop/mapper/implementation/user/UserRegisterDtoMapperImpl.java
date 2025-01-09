package com.openclassrooms.chatop.mapper.implementation.user;

import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.mapper.implementation.DtoMapper;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.PasswordService;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserRegisterDtoMapperImpl implements DtoMapper<User, UserRegisterDto> {

    private final PasswordService passwordService;

    UserRegisterDtoMapperImpl(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Override
    public User toEntity(UserRegisterDto dto) {

        if (dto.getCreatedAt() == null) {
            dto.setCreatedAt(Instant.now());
        }

        if (dto.getUpdatedAt() == null) {
            dto.setUpdatedAt(Instant.now());
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordService.hashPassword(dto.getPassword()));
        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());
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
