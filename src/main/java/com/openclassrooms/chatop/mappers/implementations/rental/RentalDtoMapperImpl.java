package com.openclassrooms.chatop.mappers.implementations.rental;

import com.openclassrooms.chatop.dto.rental.RentalCreateDto;
import com.openclassrooms.chatop.mappers.implementations.DtoMapper;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.UserRepository;
import com.openclassrooms.chatop.services.DateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RentalDtoMapperImpl implements DtoMapper<Rental, RentalCreateDto> {

    private final UserRepository userRepository;
    private final DateService dateService;

    public RentalDtoMapperImpl(UserRepository userRepository, DateService dateService) {
        this.userRepository = userRepository;
        this.dateService = dateService;
    }

    @Override
    public Rental toEntity(RentalCreateDto dto) {

        User user = this.userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with ID %d not found", dto.getOwnerId())));

        Instant now = Instant.now();
        Instant createdAt = (dto.getCreatedAt() != null)
                ? dateService.DateFormatStringToInstant(dto.getCreatedAt())
                : now;
        Instant updatedAt = (dto.getUpdatedAt() != null)
                ? dateService.DateFormatStringToInstant(dto.getUpdatedAt())
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
    public RentalCreateDto toDto(Rental rental) {
        RentalCreateDto dto = new RentalCreateDto();
        dto.setName(rental.getName());
        dto.setCreatedAt(dateService.instantObjectToDateFormatString(rental.getCreatedAt()));
        dto.setUpdatedAt(dateService.instantObjectToDateFormatString(rental.getUpdatedAt()));
        dto.setOwnerId(rental.getOwner().getId());
        dto.setDescription(rental.getDescription());
        dto.setPrice(rental.getPrice());
        return dto;
    }

}
