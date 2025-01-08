package com.openclassrooms.chatop.dto.mapper.implementation.rental;

import com.openclassrooms.chatop.dto.mapper.implementation.DTOMapper;
import com.openclassrooms.chatop.dto.rental.RentalResponseDto;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.services.DateService;
import org.springframework.stereotype.Component;


@Component
public class RentalResponseDtoMapperImpl implements DTOMapper<Rental, RentalResponseDto> {

    private final DateService dateService;

    RentalResponseDtoMapperImpl(DateService dateService) {
        this.dateService = dateService;
    }

    @Override
    public Rental toEntity(RentalResponseDto dto) {
        return null;
    }

    @Override
    public RentalResponseDto toDto(Rental rental) {
        RentalResponseDto dto = new RentalResponseDto();
        dto.setId(rental.getId());
        dto.setPicture(rental.getPicture());
        dto.setName(rental.getName());
        dto.setCreatedAt(dateService.formatInstantToDateString(rental.getCreatedAt()));
        dto.setUpdatedAt(dateService.formatInstantToDateString(rental.getUpdatedAt()));
        dto.setOwnerId(rental.getOwner().getId());
        dto.setDescription(rental.getDescription());
        dto.setPrice(rental.getPrice());
        dto.setSurface(rental.getSurface());
        return dto;
    }

}
