package com.openclassrooms.chatop.services.implementations;

import com.openclassrooms.chatop.dto.rental.RentalCreateDto;
import com.openclassrooms.chatop.dto.rental.RentalResponseDto;
import com.openclassrooms.chatop.dto.rental.RentalUpdateDto;
import com.openclassrooms.chatop.dto.rental.RentalsResponseDto;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.exceptions.NotFoundException;

import com.openclassrooms.chatop.mappers.implementations.RentalDtoMapper;
import com.openclassrooms.chatop.mappers.implementations.rental.RentalResponseDtoMapperImpl;
import com.openclassrooms.chatop.repositories.RentalRepository;
import com.openclassrooms.chatop.services.FileService;
import com.openclassrooms.chatop.services.RentalService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalDtoMapper rentalDtoMapper;
    private final RentalRepository rentalRepository;
    private final RentalResponseDtoMapperImpl rentalResponseDtoMapper;
    private final FileService fileService;

    RentalServiceImpl(
            RentalDtoMapper rentalDtoMapper,
            RentalRepository rentalRepository,
            RentalResponseDtoMapperImpl rentalResponseDtoMapper,
            FileService fileService
    ) {
        this.rentalDtoMapper = rentalDtoMapper;
        this.rentalRepository = rentalRepository;
        this.rentalResponseDtoMapper = rentalResponseDtoMapper;
        this.fileService = fileService;
    }

    @Override
    public Rental rentalDtoToRental(RentalCreateDto rentalDTO) {
        return rentalDtoMapper.toEntity(rentalDTO);
    }

    @Override
    public RentalResponseDto rentalToRentalResponseDto(Rental rental) {
        return this.rentalResponseDtoMapper.toDto(rental);
    }

    @Override
    public void saveRental(Rental rental) {
        rentalRepository.save(rental);
    }

    @Override
    public Rental findRentalById(int id) {
        return this.rentalRepository.findById(id).orElse(null);
    }

    @Override
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
    @Override
    public List<RentalResponseDto> findAllRentals() {
        List<Rental> rentals = (List<Rental>) this.rentalRepository.findAll();
        return rentals.stream().map(this.rentalResponseDtoMapper::toDto).toList();
    }

    @Override
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
    @Override
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

    @Override
    public RentalsResponseDto getAllRentalsResponse() {
        return new RentalsResponseDto(this.findAllRentals());
    }
}
