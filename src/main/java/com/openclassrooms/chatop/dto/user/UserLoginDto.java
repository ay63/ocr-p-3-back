package com.openclassrooms.chatop.dto.user;

import com.openclassrooms.chatop.constraints.password.ValidPassword;
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
    private String email;

    @NotBlank
    @NotNull
    @ValidPassword
    @Size(min = 8, max = 255)
    private String password;

}
