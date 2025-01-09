package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.rental.RentalDto;
import com.openclassrooms.chatop.dto.rental.RentalResponseDto;
import com.openclassrooms.chatop.dto.rental.RentalUpdateDto;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.exceptions.MissingPictureException;
import com.openclassrooms.chatop.exceptions.NotFoundException;
import com.openclassrooms.chatop.exceptions.UnauthorizedException;
import com.openclassrooms.chatop.services.RentalService;
import com.openclassrooms.chatop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public ResponseEntity<Map<String, String>> createRental(
            @Valid @ModelAttribute RentalDto rentalDTO,
            Authentication authentication
    ) throws Exception {

        User user = this.userService.findUserByEmail(authentication.getName());
        if (user == null) throw new UnauthorizedException();

        if (rentalDTO.getPicture().isEmpty()) throw new MissingPictureException();

        Rental rental = this.rentalService.createRentalWithFileUpload(user, rentalDTO);
        if (rental == null) throw new Exception("Rental not created");

        rentalService.saveRental(rental);

        return ResponseEntity.ok().body(Map.of("message", "Rental created !"));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalResponseDto> getRental(@PathVariable("id") int id) {
        Rental rental = this.rentalService.findRentalById(id);
        if (rental == null) {
            throw new UnauthorizedException();
        }
        RentalResponseDto rentalResponseDTO = rentalService.rentalToRentalResponseDto(rental);
        return ResponseEntity.ok().body(rentalResponseDTO);
    }

    @GetMapping(path = "")
    public ResponseEntity<Map<String, List<RentalResponseDto>>> getAllRentals() {
        List<RentalResponseDto> rentalResponseDtos = this.rentalService.findAllRentals();
        Map<String, List<RentalResponseDto>> response = new HashMap<>();
        response.put("rentals", rentalResponseDtos);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> updateRental(
            @PathVariable("id") int id,
            @Valid @ModelAttribute RentalUpdateDto rentalUpdateDTO
    ) {
        Rental rental = this.rentalService.findRentalById(id);

        if (rental == null) throw new NotFoundException();

        this.rentalService.updateRental(rental, rentalUpdateDTO);
        this.rentalService.saveRental(rental);

        return ResponseEntity.ok().body(Map.of("message", "Rental updated !"));
    }

}