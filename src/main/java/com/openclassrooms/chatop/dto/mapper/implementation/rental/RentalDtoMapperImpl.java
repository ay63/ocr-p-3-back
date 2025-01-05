package com.openclassrooms.chatop.dto.mapper.implementation.rental;

import com.openclassrooms.chatop.dto.rental.RentalDto;
import com.openclassrooms.chatop.dto.mapper.implementation.DTOMapper;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class RentalDtoMapperImpl implements DTOMapper<Rental, RentalDto> {

    private final UserRepository userRepository;

    public RentalDtoMapperImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public Rental toEntity(RentalDto dto) {

        User user = this.userRepository.findById(dto.getOwnerId()).orElse(null);

        if (user == null) {
            return null;
        }

        Rental rental = new Rental();
        rental.setName(dto.getName());
        rental.setCreatedAt(dto.getCreatedAt());
        rental.setUpdatedAt(dto.getUpdatedAt());
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
        dto.setCreatedAt(rental.getCreatedAt());
        dto.setUpdatedAt(rental.getUpdatedAt());
        dto.setOwnerId(rental.getOwner().getId());
        dto.setDescription(rental.getDescription());
        dto.setPrice(rental.getPrice());
        return dto;
    }

}
