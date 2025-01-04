package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dto.user.UserDto;
import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.dto.mapper.implementation.user.UserDtoMapperImpl;
import com.openclassrooms.chatop.dto.mapper.implementation.user.UserRegisterDtoMapperImpl;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRegisterDtoMapperImpl userRegisterMapperImpl;
    private final PasswordService passwordService;
    private final UserDtoMapperImpl userDTOMapperImpl;

    UserService(UserRepository userRepository,
                UserRegisterDtoMapperImpl userRegisterMapperImpl,
                PasswordService passwordService,
                UserDtoMapperImpl userDTOMapperImpl
    ) {
        this.userRepository = userRepository;
        this.userRegisterMapperImpl = userRegisterMapperImpl;
        this.passwordService = passwordService;
        this.userDTOMapperImpl = userDTOMapperImpl;
    }

    public boolean isUserExist(String email) {
        return this.userRepository.findByEmail(email) != null;
    }

    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User createUser(UserRegisterDto userRegisterDTO) {
        return this.userRegisterMapperImpl.toEntity(userRegisterDTO);
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public boolean userHasValidCredentials(UserLoginDto userLoginDTO) {
        User user = this.userRepository.findByEmail(userLoginDTO.getLogin());
        return passwordService.checkPassword(userLoginDTO.getPassword(), user.getPassword());
    }

    public UserDto buildUserDTO(User user) {
        return this.userDTOMapperImpl.toDto(user);
    }

}
