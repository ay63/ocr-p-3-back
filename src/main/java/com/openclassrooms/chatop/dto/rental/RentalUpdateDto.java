package com.openclassrooms.chatop.dto.rental;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalUpdateDto {

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    @Positive
    private Double surface;

    @NotNull
    @Positive
    private Double price;

    @NotBlank
    @NotNull
    private String description;

}
