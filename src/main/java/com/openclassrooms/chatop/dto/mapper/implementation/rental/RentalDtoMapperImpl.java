package com.openclassrooms.chatop.dto.mapper.implementation.rental;

import com.openclassrooms.chatop.dto.rental.RentalDto;
import com.openclassrooms.chatop.dto.mapper.implementation.DTOMapper;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.UserRepository;
import com.openclassrooms.chatop.services.DateService;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class RentalDtoMapperImpl implements DTOMapper<Rental, RentalDto> {

    private final UserRepository userRepository;
    private final DateService dateService;

    public RentalDtoMapperImpl(UserRepository userRepository, DateService dateService) {

        this.userRepository = userRepository;
        this.dateService = dateService;
    }

    @Override
    public Rental toEntity(RentalDto dto) {

        User user = this.userRepository.findById(dto.getOwnerId()).orElse(null);

        if (user == null) return null;

        Rental rental = new Rental();
        if (dto.getCreatedAt() == null) {
            rental.setCreatedAt(Instant.now());
        } else {
            rental.setCreatedAt(dateService.formatStringDateToInstant(dto.getCreatedAt()));
        }

        if(dto.getUpdatedAt() == null){
            rental.setUpdatedAt(Instant.now());
        }else{
            rental.setUpdatedAt(dateService.formatStringDateToInstant(dto.getUpdatedAt()));
        }

        rental.setName(dto.getName());
        rental.setOwner(user);
        rental.setDescription(dto.getDescription());
        rental.setPrice(dto.getPrice());
        rental.setSurface(dto.getSurface());

        return rental;
    }

    @Override
    public RentalDto toDto(Rental rental) {
        RentalDto dto = new RentalDto();
        dto.setName(rental.getName());
        dto.setCreatedAt(dateService.formatInstantToDateString(rental.getCreatedAt()));
        dto.setUpdatedAt(dateService.formatInstantToDateString(rental.getUpdatedAt()));
        dto.setOwnerId(rental.getOwner().getId());
        dto.setDescription(rental.getDescription());
        dto.setPrice(rental.getPrice());
        return dto;
    }

}
