package com.openclassrooms.chatop.mappers.implementations.user;

import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.mappers.implementations.DtoMapper;
import com.openclassrooms.chatop.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserLoginDtoMapperImpl implements DtoMapper<User, UserLoginDto> {

    @Override
    public User toEntity(UserLoginDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    @Override
    public UserLoginDto toDto(User user) {
        UserLoginDto dto = new UserLoginDto();
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
