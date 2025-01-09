package com.openclassrooms.chatop.dto.user;

import com.openclassrooms.chatop.entities.Timestampable;
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
    @Size(min = 4, max = 255)
    private String name;

    @NotBlank
    @NotNull
    @Size(min = 8, max = 255)
    private String password;

    private Instant createdAt;

    private Instant updatedAt;



}
