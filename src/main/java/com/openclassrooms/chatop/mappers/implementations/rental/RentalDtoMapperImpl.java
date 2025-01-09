package com.openclassrooms.chatop.mappers.implementations.rental;

import com.openclassrooms.chatop.dto.rental.RentalDto;
import com.openclassrooms.chatop.mappers.implementations.DtoMapper;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.UserRepository;
import com.openclassrooms.chatop.services.DateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class RentalDtoMapperImpl implements DtoMapper<Rental, RentalDto> {

    private final UserRepository userRepository;
    private final DateService dateService;

    public RentalDtoMapperImpl(UserRepository userRepository, DateService dateService) {
        this.userRepository = userRepository;
        this.dateService = dateService;
    }

    @Override
    public Rental toEntity(RentalDto dto) {

        User user = this.userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with ID %d not found", dto.getOwnerId())));

        Instant now = Instant.now();
        Instant createdAt = (dto.getCreatedAt() != null)
                ? dateService.formatStringDateToInstant(dto.getCreatedAt())
                : now;
        Instant updatedAt = (dto.getUpdatedAt() != null)
                ? dateService.formatStringDateToInstant(dto.getUpdatedAt())
                : now;

        Rental rental = new Rental();
        rental.setCreatedAt(createdAt);
        rental.setUpdatedAt(updatedAt);
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
