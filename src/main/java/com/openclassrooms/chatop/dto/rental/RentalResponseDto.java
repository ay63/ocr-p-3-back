package com.openclassrooms.chatop.dto.rental;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalResponseDto extends BaseRentalDto {

    private Integer id;

    private String picture;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;
}
