package com.openclassrooms.chatop.dto.user;

import com.openclassrooms.chatop.entities.interfaces.Timestampable;
import com.openclassrooms.chatop.constraints.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserRegisterDto implements Timestampable {

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

    private Instant createdAt;

    private Instant updatedAt;



}
