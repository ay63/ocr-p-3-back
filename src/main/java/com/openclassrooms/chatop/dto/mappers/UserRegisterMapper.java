package com.openclassrooms.chatop.dto.mappers;

import com.openclassrooms.chatop.dto.UserRegisterDTO;
import com.openclassrooms.chatop.entities.User;

public interface UserRegisterMapper {

    User toEntity(UserRegisterDTO dto);
    UserRegisterDTO toDto(User user);

}
