package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.rental.RentalsResponseDto;
import com.openclassrooms.chatop.dto.response.GenericResponseDto;
import com.openclassrooms.chatop.dto.rental.RentalCreateDto;
import com.openclassrooms.chatop.dto.rental.RentalResponseDto;
import com.openclassrooms.chatop.dto.rental.RentalUpdateDto;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.exceptions.NotFoundException;
import com.openclassrooms.chatop.services.RentalService;
import com.openclassrooms.chatop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("rentals")
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;

    public RentalController(
            RentalService rentalService,
            UserService userService
    ) {
        this.rentalService = rentalService;
        this.userService = userService;
    }

    @PostMapping(path = "")
    public ResponseEntity<GenericResponseDto> createRental(
            @Valid @ModelAttribute RentalCreateDto rentalDTO,
            Authentication authentication
    ) throws Exception {

        User user = this.userService.findUserByEmail(authentication.getName());
        if (user == null) throw new NotFoundException();

        Rental rental = this.rentalService.createRentalWithFileUpload(user, rentalDTO);
        rentalService.saveRental(rental);

        return ResponseEntity.ok().body(new GenericResponseDto("Rental created !"));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalResponseDto> getRental(@PathVariable("id") int id) {
        Rental rental = this.rentalService.findRentalById(id);
        if (rental == null) throw new NotFoundException();

        return ResponseEntity.ok().body(rentalService.rentalToRentalResponseDto(rental));
    }

    @GetMapping(path = "")
    public ResponseEntity<RentalsResponseDto> getAllRentals() {
        return ResponseEntity.ok().body(this.rentalService.getAllRentalsResponse());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<GenericResponseDto> updateRental(
            @PathVariable("id") int id,
            @Valid @ModelAttribute RentalUpdateDto rentalUpdateDTO
    ) {
        Rental rental = this.rentalService.findRentalById(id);
        if (rental == null) throw new NotFoundException();

        this.rentalService.updateAndSaveRental(rental, rentalUpdateDTO);

        return ResponseEntity.ok().body(new GenericResponseDto("Rental updated !"));
    }

}