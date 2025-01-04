package com.openclassrooms.chatop.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {

    @Email
    @NotBlank
    @NotNull
    @Size(max = 255)
    private String email;

    @NotBlank
    @NotNull
    @Size(max = 255)
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 255)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
