package com.openclassrooms.chatop.dto.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.chatop.entities.interfaces.Timestampable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MessageDto implements Timestampable {

    @JsonProperty("rental_id")
    @NotNull
    private Integer rentalId;

    @JsonProperty("user_id")
    @NotNull
    private Integer userId;

    @NotBlank
    @NotNull
    private String message;

    @JsonIgnore
    private Instant createdAt;

    @JsonIgnore
    private Instant updatedAt;


}
