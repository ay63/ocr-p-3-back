package com.openclassrooms.chatop.mappers.implementations;

import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.entities.User;

public interface UserLoginDtoMapper extends DtoMapper<User, UserLoginDto> {

    User toEntity(UserLoginDto dto);

    UserLoginDto toDto(User user);
}
