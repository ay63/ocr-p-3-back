package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.UserDTO;
import com.openclassrooms.chatop.services.JwtService;
import com.openclassrooms.chatop.services.UserService;
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
    public String register(@RequestBody UserDTO userDTO) {


        boolean userExist = this.userService.isUserExist(userDTO.getEmail());



        return "TOTO";
    }

    @PostMapping("/login")
    public String login() {
        ///@todo use Authentication class and pass to generateToken
        return jwtService.generateToken();
    }

    @GetMapping("/me")
    public String me() {
        return "I am me";
    }

}

