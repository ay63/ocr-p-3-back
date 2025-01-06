package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.user.UserDto;
import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.dto.mapper.implementation.user.UserLoginDtoMapperImpl;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.exceptions.BadRequestException;
import com.openclassrooms.chatop.exceptions.UnauthorizedException;
import com.openclassrooms.chatop.services.JwtService;
import com.openclassrooms.chatop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserService userService;
    private final UserLoginDtoMapperImpl userLoginMapperImpl;

    public AuthController(JwtService jwtService, UserService userService, UserLoginDtoMapperImpl userLoginMapperImpl) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userLoginMapperImpl = userLoginMapperImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody UserRegisterDto userRegisterDTO) {
        boolean userExist = userService.isUserExist(userRegisterDTO.getEmail());

        if (userExist) throw new BadRequestException();

        User newUser = userService.createUser(userRegisterDTO);
        userService.save(newUser);
        return ResponseEntity.ok().body(Map.of("token", jwtService.generateToken(newUser)));

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody UserLoginDto userLoginDTO) {
        if (!userService.userHasValidCredentials(userLoginDTO)) throw new UnauthorizedException();

        User user = this.userService.userLoginDtoToUser(userLoginDTO);
        return ResponseEntity.ok().body(Map.of("token", jwtService.generateToken(user)));
    }


    @GetMapping("/me")
    public ResponseEntity<UserDto> me(Authentication authentication) {
        User user = this.userService.findUserByEmail(authentication.getName());
        UserDto userDTO = this.userService.buildUserDTO(user);

        if (user == null) throw new UnauthorizedException();

        return ResponseEntity.ok().body(userDTO);
    }

}

