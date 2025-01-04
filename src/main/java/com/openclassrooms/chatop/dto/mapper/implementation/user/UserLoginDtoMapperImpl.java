package com.openclassrooms.chatop.dto.mapper.implementation.user;

import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.dto.mapper.implementation.DTOMapper;
import com.openclassrooms.chatop.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserLoginDtoMapperImpl implements DTOMapper<User, UserLoginDto> {

    @Override
    public User toEntity(UserLoginDto dto) {
        User user = new User();
        user.setEmail(dto.getLogin());
        user.setPassword(dto.getPassword());
        return user;
    }

    @Override
    public UserLoginDto toDto(User user) {
        UserLoginDto dto = new UserLoginDto();
        dto.setPassword(user.getPassword());
        dto.setLogin(user.getEmail());
        return dto;
    }
}
