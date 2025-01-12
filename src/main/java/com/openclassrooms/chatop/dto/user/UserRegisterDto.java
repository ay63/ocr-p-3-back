package com.openclassrooms.chatop.dto.user;

import com.openclassrooms.chatop.constraints.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {

    @Email
    @NotBlank
    @NotNull
    @Size(min = 6, max = 255)
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 255)
    private String name;

    @ValidPassword
    @Size(max = 255)
    private String password;

}
