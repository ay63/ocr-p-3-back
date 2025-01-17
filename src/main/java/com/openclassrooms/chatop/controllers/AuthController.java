package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.token.tokenResponseDto;
import com.openclassrooms.chatop.dto.user.UserResponseDto;
import com.openclassrooms.chatop.dto.user.UserLoginDto;
import com.openclassrooms.chatop.dto.user.UserRegisterDto;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.JwtService;
import com.openclassrooms.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
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

    @Operation(
            description = "Create user",
            tags = {"Auth"},
            security = {}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "return token",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = tokenResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "{}"
                            ))

            ),
    })
    @PostMapping("/register")
    public ResponseEntity<tokenResponseDto> register(@Valid @RequestBody UserRegisterDto userRegisterDTO) {
        this.userService.throwErrorIfUserAlreadyExist(userRegisterDTO.getEmail());
        User newUser = userService.userRegisterDtoToUser(userRegisterDTO);
        userService.saveUser(newUser);

        return ResponseEntity.ok().body(new tokenResponseDto(jwtService.generateToken(newUser)));
    }

    @Operation(
            description = "Login user",
            tags = {"Auth"},
            security = {}
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserLoginDto.class)
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "return token",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = tokenResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "{ \"message\" : \"error\"}"
                            ))

            )
    })
    @PostMapping("/login")
    public ResponseEntity<tokenResponseDto> login(@Valid @RequestBody UserLoginDto userLoginDTO) {
        this.userService.userHasValidCredentials(userLoginDTO);

        return ResponseEntity.ok().body(
                new tokenResponseDto(
                        jwtService.generateToken(this.userService.userLoginDtoToUser(userLoginDTO))
                ));
    }


    @Operation(
            description = "Get current login user",
            tags = {"Auth"}
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
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> me(Authentication authentication) {
        User user = this.userService.findUserByEmailOrThrowError(authentication.getName());
        return ResponseEntity.ok().body(this.userService.userToUserDto(user));
    }

}

