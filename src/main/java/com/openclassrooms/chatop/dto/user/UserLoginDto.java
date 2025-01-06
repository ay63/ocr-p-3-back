package com.openclassrooms.chatop.dto.user;

import jakarta.validation.constraints.*;

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
