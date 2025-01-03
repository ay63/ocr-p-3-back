package com.openclassrooms.chatop.dto.mappers;

import com.openclassrooms.chatop.dto.UserLoginDTO;
import com.openclassrooms.chatop.entities.User;

public interface UserLoginMapper {

    User toEntity(UserLoginDTO dto);
    UserLoginDTO toDto(User user);
}
