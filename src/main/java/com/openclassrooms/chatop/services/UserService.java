package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dto.user.UserResponseDto;
import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.exceptions.BadRequestException;
import com.openclassrooms.chatop.exceptions.NotFoundException;
import com.openclassrooms.chatop.exceptions.UnauthorizedException;
import com.openclassrooms.chatop.mappers.implementations.user.UserResponseDtoMapperImpl;
import com.openclassrooms.chatop.mappers.implementations.user.UserLoginDtoMapperImpl;
import com.openclassrooms.chatop.mappers.implementations.user.UserRegisterDtoMapperImpl;
import com.openclassrooms.chatop.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRegisterDtoMapperImpl userRegisterMapperImpl;
    private final PasswordService passwordService;
    private final UserResponseDtoMapperImpl userDtoMapperImpl;
    private final UserLoginDtoMapperImpl userLoginDtoMapperImpl;

    UserService(UserRepository userRepository,
                UserRegisterDtoMapperImpl userRegisterMapperImpl,
                PasswordService passwordService,
                UserResponseDtoMapperImpl userDtoMapperImpl,
                UserLoginDtoMapperImpl userLoginDtoMapperImpl
    ) {
        this.userRepository = userRepository;
        this.userRegisterMapperImpl = userRegisterMapperImpl;
        this.passwordService = passwordService;
        this.userDtoMapperImpl = userDtoMapperImpl;
        this.userLoginDtoMapperImpl = userLoginDtoMapperImpl;
    }

    public boolean isUserExist(String email) {
        return this.userRepository.findByEmail(email) != null;
    }

    public void checkAndThrowUserExist(String email) {
        if (this.isUserExist(email)) {
            throw new BadRequestException();
        }
    }

    public User findUserByEmailOrThrowError(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException();
        }

        return user;
    }

    public User findUserById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User findUserByIdOrThrowError(int id) {
        User user = this.findUserById(id);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    public User userRegisterDtoToUser(UserRegisterDto userRegisterDTO) {
        return this.userRegisterMapperImpl.toEntity(userRegisterDTO);
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public void userHasValidCredentials(UserLoginDto userLoginDTO) {
        User user = this.userRepository.findByEmail(userLoginDTO.getEmail());
        if (user == null) {
            throw new NotFoundException();
        }

        if (!this.passwordService.checkPassword(userLoginDTO.getPassword(), user.getPassword())) {
            throw new UnauthorizedException();
        }
    }

    public UserResponseDto userToUserDto(User user) {
        return this.userDtoMapperImpl.toDto(user);
    }

    public User userLoginDtoToUser(UserLoginDto userLoginDTO) {
        return this.userLoginDtoMapperImpl.toEntity(userLoginDTO);
    }

}
