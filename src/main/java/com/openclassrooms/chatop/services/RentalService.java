package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dto.mapper.implementation.rental.RentalResponseDtoMapperImpl;
import com.openclassrooms.chatop.dto.rental.RentalDto;
import com.openclassrooms.chatop.dto.mapper.implementation.rental.RentalDtoMapperImpl;
import com.openclassrooms.chatop.dto.rental.RentalResponseDto;
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
    private final RentalResponseDtoMapperImpl rentalResponseDtoMapper;

    RentalService(
            RentalDtoMapperImpl rentalDTOMapperImpl,
            RentalRepository rentalRepository,
            RentalResponseDtoMapperImpl rentalResponseDtoMapper
    ) {
        this.rentalDTOMapperImpl = rentalDTOMapperImpl;
        this.rentalRepository = rentalRepository;
        this.rentalResponseDtoMapper = rentalResponseDtoMapper;
    }

    /**
     * Converts a RentalDto to a Rental entity.
     *
     * @param rentalDTO RentalDto
     * @return Rental
     */
    public Rental buildRental(RentalDto rentalDTO) {
        return rentalDTOMapperImpl.toEntity(rentalDTO);
    }

    /**
     * Converts a Rental entity to a RentalResponseDto.
     *
     * @param rental rental
     * @return RentalResponseDto
     */
    public RentalResponseDto buildRentalResponseDTO(Rental rental) {
        return this.rentalResponseDtoMapper.toDto(rental);
    }

    /**
     * Saves the given Rental entity to the repository.
     *
     * @param rental Rental
     */
    public void save(Rental rental) {
        rentalRepository.save(rental);
    }

    /**
     * Finds a Rental entity by its ID.
     *
     * @param id int
     * @return Rental
     */
    public Rental findRentalById(int id) {
        return this.rentalRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all Rental entities and converts them to RentalResponseDto.
     *
     * @return List<RentalResponseDto>
     */
    public List<RentalResponseDto> findAllRentals() {
        List<Rental> rentals = (List<Rental>) this.rentalRepository.findAll();
        return rentals.stream().map(this.rentalResponseDtoMapper::toDto).toList();
    }

    /**
     * Updates an existing Rental entity with data from RentalUpdateDto.
     *
     * @param rental          Rental
     * @param rentalUpdateDTO RentalUpdateDto
     */
    public void updateRental(Rental rental, RentalUpdateDto rentalUpdateDTO) {
        rental.setUpdatedAt(Instant.now());
        rental.setSurface(rentalUpdateDTO.getSurface());
        rental.setPrice(rentalUpdateDTO.getPrice());
        rental.setDescription(rentalUpdateDTO.getDescription());
        rental.setName(rentalUpdateDTO.getName());
        this.rentalRepository.save(rental);
    }

}
