package com.openclassrooms.chatop.dto.implementation;

import com.openclassrooms.chatop.dto.UserLoginDTO;
import com.openclassrooms.chatop.dto.mappers.UserLoginMapper;
import com.openclassrooms.chatop.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserLoginMapperImpl implements UserLoginMapper {

    @Override
    public User toEntity(UserLoginDTO dto) {
        User user = new User();
        user.setEmail(dto.getLogin());
        user.setPassword(dto.getPassword());
        return user;
    }

    @Override
    public UserLoginDTO toDto(User user) {
        UserLoginDTO dto = new UserLoginDTO();
        dto.setPassword(user.getPassword());
        dto.setLogin(user.getEmail());
        return dto;
    }
}
