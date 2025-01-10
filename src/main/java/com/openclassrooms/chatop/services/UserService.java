package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dto.user.UserResponseDto;
import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.entities.User;
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

    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User findUserById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User userRegisterDtoToUser(UserRegisterDto userRegisterDTO) {
        return this.userRegisterMapperImpl.toEntity(userRegisterDTO);
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public boolean userHasValidCredentials(UserLoginDto userLoginDTO) {
        User user = this.userRepository.findByEmail(userLoginDTO.getLogin());
        return passwordService.checkPassword(userLoginDTO.getPassword(), user.getPassword());
    }

    public UserResponseDto userToUserDto(User user) {
        return this.userDtoMapperImpl.toDto(user);
    }

    public User userLoginDtoToUser(UserLoginDto userLoginDTO) {
        return this.userLoginDtoMapperImpl.toEntity(userLoginDTO);
    }

}
