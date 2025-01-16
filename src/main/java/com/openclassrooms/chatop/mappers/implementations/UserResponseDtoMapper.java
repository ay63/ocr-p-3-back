package com.openclassrooms.chatop.mappers.implementations;

import com.openclassrooms.chatop.dto.user.UserResponseDto;
import com.openclassrooms.chatop.entities.User;

public interface UserResponseDtoMapper extends DtoMapper<User, UserResponseDto> {

    User toEntity(UserResponseDto dto);

    UserResponseDto toDto(User user);

}
