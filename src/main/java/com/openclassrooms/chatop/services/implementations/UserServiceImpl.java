package com.openclassrooms.chatop.services.implementations;

import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.dto.user.UserResponseDto;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.exceptions.NotFoundException;
import com.openclassrooms.chatop.exceptions.UnauthorizedException;
import com.openclassrooms.chatop.mappers.implementations.UserLoginDtoMapper;
import com.openclassrooms.chatop.mappers.implementations.UserRegisterDtoMapper;
import com.openclassrooms.chatop.mappers.implementations.UserResponseDtoMapper;

import com.openclassrooms.chatop.repositories.UserRepository;
import com.openclassrooms.chatop.services.PasswordService;
import com.openclassrooms.chatop.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserRegisterDtoMapper userRegisterMapper;
    private final PasswordService passwordService;
    private final UserResponseDtoMapper userDtoMapper;
    private final UserLoginDtoMapper userLoginDtoMapper;

    UserServiceImpl(UserRepository userRepository,
                    UserRegisterDtoMapper userRegisterMapper,
                    PasswordService passwordService,
                    UserResponseDtoMapper userDtoMapper,
                    UserLoginDtoMapper userLoginDtoMapper
    ) {
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
        this.passwordService = passwordService;
        this.userDtoMapper = userDtoMapper;
        this.userLoginDtoMapper = userLoginDtoMapper;
    }

    @Override
    public void throwErrorIfUserAlreadyExist(String email) {
        if (this.userRepository.findByEmail(email) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public User findUserByEmailOrThrowError(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException();
        }

        return user;
    }

    @Override
    public User findUserById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserByIdOrThrowError(int id) {
        User user = this.findUserById(id);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    @Override
    public User userRegisterDtoToUser(UserRegisterDto userRegisterDTO) {
        return this.userRegisterMapper.toEntity(userRegisterDTO);
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void userHasValidCredentials(UserLoginDto userLoginDTO) {
        UserDetails user = this.loadUserByUsername(userLoginDTO.getEmail());

        if (!this.passwordService.checkPassword(userLoginDTO.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("error");
        }
    }

    @Override
    public UserResponseDto userToUserDto(User user) {
        return this.userDtoMapper.toDto(user);
    }

    @Override
    public User userLoginDtoToUser(UserLoginDto userLoginDTO) {
        return this.userLoginDtoMapper.toEntity(userLoginDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDetails user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new UnauthorizedException("error");
        }
        return user;
    }
}
