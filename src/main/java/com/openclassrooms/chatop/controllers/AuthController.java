package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.UserLoginDTO;
import com.openclassrooms.chatop.dto.UserRegisterDTO;
import com.openclassrooms.chatop.dto.implementation.UserLoginMapperImpl;
import com.openclassrooms.chatop.dto.implementation.UserRegisterMapperImpl;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.JwtService;
import com.openclassrooms.chatop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserService userService;
    private final UserRegisterMapperImpl userRegisterMapperImpl;
    private final UserLoginMapperImpl userLoginMapperImpl;

    public AuthController(JwtService jwtService,
                          UserService userService,
                          UserRegisterMapperImpl userRegisterMapperImpl,
                          UserLoginMapperImpl userLoginMapperImpl)
    {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userRegisterMapperImpl = userRegisterMapperImpl;
        this.userLoginMapperImpl = userLoginMapperImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        boolean userExist = userService.isUserExist(userRegisterDTO.getEmail());

        if (!userExist) {
            User newUser = userService.createUser(userRegisterDTO);
            userService.save(newUser);
            return ResponseEntity
                    .ok().body(Map.of("token", jwtService.generateToken(newUser)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {

        if (userService.userHasValidCredentials(userLoginDTO)) {
            User user = userLoginMapperImpl.toEntity(userLoginDTO);
            return ResponseEntity
                    .ok().body(Map.of("token", jwtService.generateToken(user)));
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED).build();
    }


    //@TODO see mockoon for respons
    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> me(Authentication authentication) {
        String me = "I am" + " " + authentication.getName();
        return ResponseEntity
                .ok(Map.of("message", me));
    }

}

