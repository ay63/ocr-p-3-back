package com.openclassrooms.chatop.mappers.implementations;

import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.entities.User;

public interface UserRegisterDtoMapper extends DtoMapper<User, UserRegisterDto> {


    User toEntity(UserRegisterDto dto);

    UserRegisterDto toDto(User user);
}
