package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.UserDTO;
import com.openclassrooms.chatop.services.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDto) {
        return "userDto";
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

