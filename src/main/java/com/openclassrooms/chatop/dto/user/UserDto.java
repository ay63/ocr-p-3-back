package com.openclassrooms.chatop.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotNull
    @NotBlank
    @Positive
    private int id;

    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    @NotBlank
    @NotNull
    @JsonIgnore
    @Size(min = 8, max = 255)
    private String password;

    @NotBlank
    @NotNull
    @Size(min = 4, max = 255)
    private String name;

    @NotNull
    @JsonProperty("created_at")
    private String createdAt;

    @NotNull
    @JsonProperty("updated_at")
    private String updatedAt;

}
