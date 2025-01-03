package com.openclassrooms.chatop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    @Email
    @NotBlank
    @NotNull
    @Size(max = 255)
    private String login;

    @NotBlank
    @NotNull
    @Size(max = 255)
    private String password;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
