package com.openclassrooms.chatop.dto.mapper.implementation.user;

import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.dto.mapper.implementation.DTOMapper;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.PasswordService;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterDtoMapperImpl implements DTOMapper<User, UserRegisterDto> {

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
