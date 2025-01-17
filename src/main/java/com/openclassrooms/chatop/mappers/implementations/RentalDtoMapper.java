package com.openclassrooms.chatop.mappers.implementations;

import com.openclassrooms.chatop.dto.rental.RentalCreateDto;
import com.openclassrooms.chatop.entities.Rental;

public interface RentalDtoMapper extends DtoMapper<Rental, RentalCreateDto> {

    Rental toEntity(RentalCreateDto dto);

    RentalCreateDto toDto(Rental rental);

}
