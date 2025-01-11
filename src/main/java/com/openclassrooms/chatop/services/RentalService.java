package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dto.rental.RentalsResponseDto;
import com.openclassrooms.chatop.exceptions.NotFoundException;
import com.openclassrooms.chatop.mappers.implementations.rental.RentalResponseDtoMapperImpl;
import com.openclassrooms.chatop.dto.rental.RentalCreateDto;
import com.openclassrooms.chatop.mappers.implementations.rental.RentalDtoMapperImpl;
import com.openclassrooms.chatop.dto.rental.RentalResponseDto;
import com.openclassrooms.chatop.dto.rental.RentalUpdateDto;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class RentalService {

    private final RentalDtoMapperImpl rentalDTOMapperImpl;
    private final RentalRepository rentalRepository;
    private final RentalResponseDtoMapperImpl rentalResponseDtoMapper;
    private final FileService fileService;

    RentalService(
            RentalDtoMapperImpl rentalDTOMapperImpl,
            RentalRepository rentalRepository,
            RentalResponseDtoMapperImpl rentalResponseDtoMapper, FileService fileService
    ) {
        this.rentalDTOMapperImpl = rentalDTOMapperImpl;
        this.rentalRepository = rentalRepository;
        this.rentalResponseDtoMapper = rentalResponseDtoMapper;
        this.fileService = fileService;
    }

    public Rental rentalDtoToRental(RentalCreateDto rentalDTO) {
        return rentalDTOMapperImpl.toEntity(rentalDTO);
    }

    public RentalResponseDto rentalToRentalResponseDto(Rental rental) {
        return this.rentalResponseDtoMapper.toDto(rental);
    }

    public void saveRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public Rental findRentalById(int id) {
        return this.rentalRepository.findById(id).orElse(null);
    }

    public Rental findRentalByUserIdOrThrowError(int id) {
        Rental rental = this.findRentalById(id);
        if (rental == null) {
            throw new NotFoundException();
        }
        return rental;
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

    public void updateAndSaveRental(Rental rental, RentalUpdateDto rentalUpdateDTO) {
        rental.setUpdatedAt(Instant.now());
        rental.setSurface(rentalUpdateDTO.getSurface());
        rental.setPrice(rentalUpdateDTO.getPrice());
        rental.setDescription(rentalUpdateDTO.getDescription());
        rental.setName(rentalUpdateDTO.getName());
        this.saveRental(rental);
    }

    /**
     * Create rental object and upload file to S3 bucket
     *
     * @param user      User
     * @param rentalDto RentalDto
     * @return Rental
     * @throws Exception
     */
    public Rental createRentalWithFileUpload(User user, RentalCreateDto rentalDto) throws Exception {
        String fileUrl = fileService.uploadFile(
                rentalDto.getPicture().getInputStream(),
                rentalDto.getPicture().getOriginalFilename()
        );

        rentalDto.setOwnerId(user.getId());
        Rental rental = this.rentalDtoToRental(rentalDto);
        rental.setPicture(fileUrl);

        return rental;
    }

    public RentalsResponseDto getAllRentalsResponse() {
        return new RentalsResponseDto(this.findAllRentals());
    }

}
