package com.openclassrooms.chatop.mappers.implementations;

import com.openclassrooms.chatop.dto.rental.RentalResponseDto;
import com.openclassrooms.chatop.entities.Rental;

public interface RentalResponseDtoMapper extends DtoMapper<Rental, RentalResponseDto> {


    Rental toEntity(RentalResponseDto dto);

    RentalResponseDto toDto(Rental rental);


}
