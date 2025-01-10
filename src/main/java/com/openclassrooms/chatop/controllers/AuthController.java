package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.token.tokenResponseDto;
import com.openclassrooms.chatop.dto.user.UserResponseDto;
import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.exceptions.BadRequestException;
import com.openclassrooms.chatop.exceptions.NotFoundException;
import com.openclassrooms.chatop.exceptions.UnauthorizedException;
import com.openclassrooms.chatop.services.JwtService;
import com.openclassrooms.chatop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(JwtService jwtService, UserService userService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<tokenResponseDto> register(@Valid @RequestBody UserRegisterDto userRegisterDTO) {
        boolean userExist = userService.isUserExist(userRegisterDTO.getEmail());
        if (userExist) throw new BadRequestException();

        User newUser = userService.userRegisterDtoToUser(userRegisterDTO);
        userService.saveUser(newUser);

        return ResponseEntity.ok().body(new tokenResponseDto(jwtService.generateToken(newUser)));
    }

    @PostMapping("/login")
    public ResponseEntity<tokenResponseDto> login(@Valid @RequestBody UserLoginDto userLoginDTO) {
        if (!userService.userHasValidCredentials(userLoginDTO)) throw new UnauthorizedException();

        return ResponseEntity.ok().body(
                new tokenResponseDto(jwtService.generateToken(this.userService.userLoginDtoToUser(userLoginDTO))
                ));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> me(Authentication authentication) {
        User user = this.userService.findUserByEmail(authentication.getName());
        if (user == null) throw new NotFoundException();

        return ResponseEntity.ok().body(this.userService.userToUserDto(user));
    }

}

