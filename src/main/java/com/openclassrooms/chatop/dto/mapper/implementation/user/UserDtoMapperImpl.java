package com.openclassrooms.chatop.dto.mapper.implementation.user;

import com.openclassrooms.chatop.dto.user.UserDto;
import com.openclassrooms.chatop.dto.mapper.implementation.DTOMapper;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.DateService;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapperImpl implements DTOMapper<User, UserDto> {

    private final DateService dateService;

    UserDtoMapperImpl(DateService dateService) {
        this.dateService = dateService;
    }

    @Override
    public User toEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCreatedAt(dateService.formatStringDateToInstant(dto.getCreatedAt()));
        user.setUpdatedAt(dateService.formatStringDateToInstant(dto.getUpdatedAt()));
        return user;
    }

    @Override
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setCreatedAt(dateService.formatInstantToDateString(user.getCreatedAt()));
        dto.setUpdatedAt(dateService.formatInstantToDateString(user.getUpdatedAt()));
        return dto;
    }
}
