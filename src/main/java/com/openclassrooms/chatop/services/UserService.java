package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dto.user.UserResponseDto;
import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.entities.User;

public interface UserService {

    void throwErrorIfUserAlreadyExist(String email);

    User findUserByEmailOrThrowError(String email);

    User findUserById(int id);

    User findUserByIdOrThrowError(int id);

    User userRegisterDtoToUser(UserRegisterDto userRegisterDTO);

    void saveUser(User user);

    void userHasValidCredentials(UserLoginDto userLoginDTO);

    UserResponseDto userToUserDto(User user);

    User userLoginDtoToUser(UserLoginDto userLoginDTO);

}
