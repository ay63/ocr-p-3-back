package com.openclassrooms.chatop.dto.mapper.implementation.rental;

import com.openclassrooms.chatop.dto.mapper.implementation.DTOMapper;
import com.openclassrooms.chatop.dto.rental.RentalResponseDto;
import com.openclassrooms.chatop.entities.Rental;
import org.springframework.stereotype.Component;


@Component
public class RentalResponseDtoMapperImpl implements DTOMapper<Rental, RentalResponseDto> {

    @Override
    public Rental toEntity(RentalResponseDto dto) {
        return null;
    }

    @Override
    public RentalResponseDto toDto(Rental rental) {
        RentalResponseDto dto = new RentalResponseDto();
        dto.setPicture(rental.getPicture());
        dto.setName(rental.getName());
        dto.setCreatedAt(rental.getCreatedAt());
        dto.setUpdatedAt(rental.getUpdatedAt());
        dto.setOwnerId(rental.getOwner().getId());
        dto.setDescription(rental.getDescription());
        dto.setPrice(rental.getPrice());
        return dto;
    }

}
