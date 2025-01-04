package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.rental.RentalDto;
import com.openclassrooms.chatop.dto.rental.RentalUpdateDto;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.RentalService;
import com.openclassrooms.chatop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController()
@RequestMapping("rentals")
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;

    public RentalController(RentalService rentalService, UserService userService) {
        this.rentalService = rentalService;
        this.userService = userService;
    }

    @PostMapping(path = "")
    public ResponseEntity<String> createRental(@Valid @ModelAttribute RentalDto rentalDTO, Authentication authentication) {
        User user = this.userService.findUserByEmail(authentication.getName());
        rentalDTO.setOwnerId(user.getId());
        Rental rental = this.rentalService.buildRental(rentalDTO);
        rentalService.save(rental);

        return ResponseEntity.ok().body("message");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalDto> getRental(@PathVariable("id") int id) {
        Rental rental = this.rentalService.findRentalById(id).orElse(null);
        RentalDto rentalDTO = rentalService.buidlRentalDTO(rental);

        if (rental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(rentalDTO);
    }

    @GetMapping(path = "")
    public Iterable<RentalDto> getAllRentals() {
        Iterable<RentalDto> rentalDTOs = this.rentalService.findAllRentals();
        return ResponseEntity.ok().body(rentalDTOs).getBody();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> updateRental(@PathVariable("id") int id, @Valid @ModelAttribute RentalUpdateDto rentalUpdateDTO) {

        Rental rental = this.rentalService.findRentalById(id).orElse(null);
        if (rental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        this.rentalService.updateRental(rental, rentalUpdateDTO);

        return ResponseEntity.ok().body(Map.of("message", "Rental updated ! "));
    }

}