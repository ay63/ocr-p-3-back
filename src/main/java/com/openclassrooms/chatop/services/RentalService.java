package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dto.rental.RentalUpdateDto;
import com.openclassrooms.chatop.dto.rental.RentalsResponseDto;

import com.openclassrooms.chatop.dto.rental.RentalCreateDto;
import com.openclassrooms.chatop.dto.rental.RentalResponseDto;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import java.util.List;


public interface RentalService {

    Rental rentalDtoToRental(RentalCreateDto rentalDTO);

    RentalResponseDto rentalToRentalResponseDto(Rental rental);

    void saveRental(Rental rental);

    Rental findRentalById(int id);

    Rental findRentalByUserIdOrThrowError(int id);

    List<RentalResponseDto> findAllRentals();

    Rental createRentalWithFileUpload(User user, RentalCreateDto rentalDto) throws Exception;

    RentalsResponseDto getAllRentalsResponse();

    void updateAndSaveRental(Rental rental, RentalUpdateDto rentalUpdateDTO);
}
