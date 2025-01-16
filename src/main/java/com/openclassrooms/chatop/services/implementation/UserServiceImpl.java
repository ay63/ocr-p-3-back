package com.openclassrooms.chatop.services.implementation;

import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.dto.user.UserResponseDto;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.exceptions.NotFoundException;
import com.openclassrooms.chatop.exceptions.UnauthorizedException;
import com.openclassrooms.chatop.mappers.implementations.user.UserLoginDtoMapperImpl;
import com.openclassrooms.chatop.mappers.implementations.user.UserRegisterDtoMapperImpl;
import com.openclassrooms.chatop.mappers.implementations.user.UserResponseDtoMapperImpl;
import com.openclassrooms.chatop.repositories.UserRepository;
import com.openclassrooms.chatop.services.PasswordService;
import com.openclassrooms.chatop.services.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepository userRepository;
    private final UserRegisterDtoMapperImpl userRegisterMapperImpl;
    private final PasswordService passwordService;
    private final UserResponseDtoMapperImpl userDtoMapperImpl;
    private final UserLoginDtoMapperImpl userLoginDtoMapperImpl;

    UserServiceImpl(UserRepository userRepository,
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

    @Override
    public void throwErrorIfUserAlreadyExist(String email) {
        if (this.userRepository.findByEmail(email) != null) {
            throw new UnauthorizedException();
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
        return this.userRegisterMapperImpl.toEntity(userRegisterDTO);
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void userHasValidCredentials(UserLoginDto userLoginDTO) {
        UserDetails user = this.loadUserByUsername(userLoginDTO.getEmail());

        if (!this.passwordService.checkPassword(userLoginDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong credentials");
        }
    }

    @Override
    public UserResponseDto userToUserDto(User user) {
        return this.userDtoMapperImpl.toDto(user);
    }

    @Override
    public User userLoginDtoToUser(UserLoginDto userLoginDTO) {
        return this.userLoginDtoMapperImpl.toEntity(userLoginDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDetails user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
