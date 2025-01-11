package com.openclassrooms.chatop.mappers.implementations.user;

import com.openclassrooms.chatop.dto.user.UserResponseDto;
import com.openclassrooms.chatop.mappers.implementations.DtoMapper;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.DateService;
import org.springframework.stereotype.Service;

@Service
public class UserResponseDtoMapperImpl implements DtoMapper<User, UserResponseDto> {

    private final DateService dateService;

    UserResponseDtoMapperImpl(DateService dateService) {
        this.dateService = dateService;
    }

    @Override
    public User toEntity(UserResponseDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCreatedAt(dateService.DateFormatStringToInstant(dto.getCreatedAt()));
        user.setUpdatedAt(dateService.DateFormatStringToInstant(dto.getUpdatedAt()));
        return user;
    }

    @Override
    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(dateService.instantObjectToDateFormatString(user.getCreatedAt()));
        dto.setUpdatedAt(dateService.instantObjectToDateFormatString(user.getUpdatedAt()));
        return dto;
    }
}
