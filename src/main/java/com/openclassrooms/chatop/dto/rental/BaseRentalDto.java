package com.openclassrooms.chatop.dto.rental;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
abstract class BaseRentalDto {

    @NotNull
    private String name;

    @NotNull
    @Positive
    private Double surface;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private String description;

    @JsonProperty("owner_id")
    private Integer ownerId;



}


