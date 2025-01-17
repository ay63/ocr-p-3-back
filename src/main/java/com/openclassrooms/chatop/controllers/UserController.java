package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.user.UserResponseDto;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            description = "Get user by id",
            tags = {"User"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "return user data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "unauthorized",
                    content = @Content()
            )
    })
    @GetMapping(path = "{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") int id) {
        User user = this.userService.findUserByIdOrThrowError(id);
        return ResponseEntity.ok(this.userService.userToUserDto(user));
    }
}
