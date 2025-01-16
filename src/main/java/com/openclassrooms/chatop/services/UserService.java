package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dto.user.UserResponseDto;
import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.exceptions.NotFoundException;
import com.openclassrooms.chatop.exceptions.UnauthorizedException;
import com.openclassrooms.chatop.mappers.implementations.user.UserResponseDtoMapperImpl;
import com.openclassrooms.chatop.mappers.implementations.user.UserLoginDtoMapperImpl;
import com.openclassrooms.chatop.mappers.implementations.user.UserRegisterDtoMapperImpl;
import com.openclassrooms.chatop.repositories.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
