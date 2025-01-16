package com.openclassrooms.chatop.mappers.implementations.rental;

import com.openclassrooms.chatop.dto.rental.RentalResponseDto;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.mappers.implementations.RentalResponseDtoMapper;
import com.openclassrooms.chatop.services.DateService;
import org.springframework.stereotype.Service;


@Service
public class RentalResponseDtoMapperImpl implements RentalResponseDtoMapper {

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
        dto.setCreatedAt(dateService.instantObjectToDateFormatString(rental.getCreatedAt()));
        dto.setUpdatedAt(dateService.instantObjectToDateFormatString(rental.getUpdatedAt()));
        dto.setOwnerId(rental.getOwner().getId());
        dto.setDescription(rental.getDescription());
        dto.setPrice(rental.getPrice());
        dto.setSurface(rental.getSurface());
        return dto;
    }

}
