package com.openclassrooms.chatop.mappers.implementations.user;

import com.openclassrooms.chatop.dto.user.UserDto;
import com.openclassrooms.chatop.mappers.implementations.DtoMapper;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.DateService;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapperImpl implements DtoMapper<User, UserDto> {

    private final DateService dateService;

    UserDtoMapperImpl(DateService dateService) {
        this.dateService = dateService;
    }

    @Override
    public User toEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
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
        dto.setCreatedAt(dateService.formatInstantToDateString(user.getCreatedAt()));
        dto.setUpdatedAt(dateService.formatInstantToDateString(user.getUpdatedAt()));
        return dto;
    }
}
