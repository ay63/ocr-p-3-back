package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dto.mapper.implementation.user.UserLoginDtoMapperImpl;
import com.openclassrooms.chatop.dto.user.UserDto;
import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.dto.mapper.implementation.user.UserDtoMapperImpl;
import com.openclassrooms.chatop.dto.mapper.implementation.user.UserRegisterDtoMapperImpl;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRegisterDtoMapperImpl userRegisterMapperImpl;
    private final PasswordService passwordService;
    private final UserDtoMapperImpl userDtoMapperImpl;
    private final UserLoginDtoMapperImpl userLoginDtoMapperImpl;

    UserService(UserRepository userRepository,
                UserRegisterDtoMapperImpl userRegisterMapperImpl,
                PasswordService passwordService,
                UserDtoMapperImpl userDtoMapperImpl,
                UserLoginDtoMapperImpl userLoginDtoMapperImpl
    ) {
        this.userRepository = userRepository;
        this.userRegisterMapperImpl = userRegisterMapperImpl;
        this.passwordService = passwordService;
        this.userDtoMapperImpl = userDtoMapperImpl;
        this.userLoginDtoMapperImpl = userLoginDtoMapperImpl;
    }

    /**
     * Checks if a user exists by email.
     *
     * @param email String
     * @return boolean
     */
    public boolean isUserExist(String email) {
        return this.userRepository.findByEmail(email) != null;
    }

    /**
     * Finds a user by email.
     *
     * @param email String
     * @return User
     */
    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    /**
     * Finds a user by ID.
     *
     * @param id int
     * @return User or null
     */
    public User findUserById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    /**
     * Creates a User entity from a UserRegisterDto.
     *
     * @param userRegisterDTO UserRegisterDto
     * @return User
     */
    public User createUser(UserRegisterDto userRegisterDTO) {
        userRegisterDTO.setCreatedAt(Instant.now());
        userRegisterDTO.setUpdatedAt(Instant.now());
        return this.userRegisterMapperImpl.toEntity(userRegisterDTO);
    }

    /**
     * Saves a User entity to the repository.
     *
     * @param user User
     */
    public void save(User user) {
        this.userRepository.save(user);
    }

    /**
     * Verifies if a user has valid credentials.
     *
     * @param userLoginDTO UserLoginDto
     * @return boolean
     */
    public boolean userHasValidCredentials(UserLoginDto userLoginDTO) {
        User user = this.userRepository.findByEmail(userLoginDTO.getLogin());
        return passwordService.checkPassword(userLoginDTO.getPassword(), user.getPassword());
    }

    /**
     * Converts a User entity to a UserDto.
     *
     * @param user User
     * @return UserDto
     */
    public UserDto userToUserDto(User user) {
        return this.userDtoMapperImpl.toDto(user);
    }

    /**
     * Converts a UserLoginDto to a User entity.
     *
     * @param userLoginDTO UserLoginDto
     * @return User
     */
    public User userLoginDtoToUser(UserLoginDto userLoginDTO) {
        return this.userLoginDtoMapperImpl.toEntity(userLoginDTO);
    }

}
