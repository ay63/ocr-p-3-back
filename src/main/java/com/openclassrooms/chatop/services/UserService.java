package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dto.UserLoginDTO;
import com.openclassrooms.chatop.dto.UserRegisterDTO;
import com.openclassrooms.chatop.dto.implementation.UserLoginMapperImpl;
import com.openclassrooms.chatop.dto.implementation.UserRegisterMapperImpl;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRegisterMapperImpl userRegisterMapperImpl;
    private final UserLoginMapperImpl userLoginMapperImpl;
    private final PasswordService passwordService;

    UserService(UserRepository userRepository,
                UserRegisterMapperImpl userRegisterMapperImpl,
                UserLoginMapperImpl userLoginMapperImpl,
                PasswordService passwordService)
    {
        this.userRepository = userRepository;
        this.userRegisterMapperImpl = userRegisterMapperImpl;
        this.userLoginMapperImpl = userLoginMapperImpl;
        this.passwordService = passwordService;
    }

    public boolean isUserExist(String email) {
        return this.userRepository.findByEmail(email) != null;
    }

    public User createUser(UserRegisterDTO userRegisterDTO) {
        return this.userRegisterMapperImpl.toEntity(userRegisterDTO);
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public boolean userHasValidCredentials(UserLoginDTO userLoginDTO) {
        User user = this.userRepository.findByEmail(userLoginDTO.getLogin());
        return passwordService.checkPassword(userLoginDTO.getPassword(), user.getPassword());
    }


}
