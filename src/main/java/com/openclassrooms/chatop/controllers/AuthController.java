package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.UserRepository;
import com.openclassrooms.chatop.services.JwtService;
import com.openclassrooms.chatop.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("auth")
public class AuthController {

    private final JwtService jwtService;

    private final UserService userService;
    private final UserRepository userRepository;

    public AuthController(JwtService jwtService, UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        boolean userExist = this.userService.isUserExist(user.getEmail());
        System.out.println(userExist);
        Map<String, String> response = new HashMap<>();
        HttpStatus status = userExist ? HttpStatus.CONFLICT : HttpStatus.OK;

        if (!userExist) {
            User newUser = this.userService.createUser(user);
            userRepository.save(newUser);
        } else {
            response.put("error", "email already in use");
        }

        return new ResponseEntity<>(response, status);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("login");
        String password = credentials.get("password");
        User user = this.userRepository.findByEmail(email);

        if (user != null) {
            if (this.userService.checkPassword(password, user.getPassword())) {
                return ResponseEntity
                        .ok(Map.of("token", jwtService.generateToken(user)));
            }
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Unvalid credentials"));
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> me(Authentication authentication) {
        String me = "I am" +" "+ authentication.getName();
        return ResponseEntity
                .ok(Map.of("message", me));
    }

}

