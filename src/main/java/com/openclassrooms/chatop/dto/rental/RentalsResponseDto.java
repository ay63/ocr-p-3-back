package com.openclassrooms.chatop.dto.rental;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class RentalsResponseDto {

    private List<RentalResponseDto> rentals;
}
