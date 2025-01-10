package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.user.UserResponseDto;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.exceptions.NotFoundException;
import com.openclassrooms.chatop.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") int id) {
        User user = this.userService.findUserById(id);
        if(user == null) throw new NotFoundException();

        UserResponseDto userDto = this.userService.userToUserDto(user);
        return ResponseEntity.ok(userDto);
    }
}
