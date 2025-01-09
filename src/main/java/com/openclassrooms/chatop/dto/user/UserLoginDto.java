package com.openclassrooms.chatop.dto.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {

    @Email
    @NotBlank
    @NotNull
    @Size(max = 255)
    private String login;

    @NotBlank
    @NotNull
    @Size(min = 8, max = 255)
    private String password;

}
