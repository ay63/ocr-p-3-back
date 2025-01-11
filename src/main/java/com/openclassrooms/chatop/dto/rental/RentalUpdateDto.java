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
    private Integer surface;

    @NotNull
    @Positive
    private Integer price;

    @NotBlank
    @NotNull
    private String description;

}
