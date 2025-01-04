package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dto.rental.RentalDto;
import com.openclassrooms.chatop.dto.mapper.implementation.rental.RentalDtoMapperImpl;
import com.openclassrooms.chatop.dto.rental.RentalUpdateDto;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.repositories.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    private final RentalDtoMapperImpl rentalDTOMapperImpl;
    private final RentalRepository rentalRepository;

    RentalService(RentalDtoMapperImpl rentalDTOMapperImpl, RentalRepository rentalRepository) {
        this.rentalDTOMapperImpl = rentalDTOMapperImpl;
        this.rentalRepository = rentalRepository;
    }

    public Rental buildRental(RentalDto rentalDTO) {
        return rentalDTOMapperImpl.toEntity(rentalDTO);
    }

    public RentalDto buidlRentalDTO(Rental rental) {
        return this.rentalDTOMapperImpl.toDto(rental);
    }

    public void save(Rental rental) {
        rental.setCreatedAt(Instant.now());
        rental.setUpdatedAt(Instant.now());
        rentalRepository.save(rental);
    }

    public Optional<Rental> findRentalById(int id) {
        return this.rentalRepository.findById(id);
    }

    public List<RentalDto> findAllRentals() {
        List<Rental> rentals = (List<Rental>) this.rentalRepository.findAll();
        return rentals.stream().map(this.rentalDTOMapperImpl::toDto).toList();
    }

    public void updateRental(Rental rental, RentalUpdateDto rentalUpdateDTO) {
        rental.setUpdatedAt(Instant.now());
        rental.setSurface(rentalUpdateDTO.getSurface());
        rental.setPrice(rentalUpdateDTO.getPrice());
        rental.setDescription(rentalUpdateDTO.getDescription());
        rental.setName(rentalUpdateDTO.getName());
        this.rentalRepository.save(rental);
    }
}
