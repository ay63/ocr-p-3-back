package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.rental.RentalDto;
import com.openclassrooms.chatop.dto.rental.RentalResponseDto;
import com.openclassrooms.chatop.dto.rental.RentalUpdateDto;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.FileService;
import com.openclassrooms.chatop.services.RentalService;
import com.openclassrooms.chatop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Map;


@RestController()
@RequestMapping("rentals")
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;
    private final FileService fileService;

    public RentalController(RentalService rentalService, UserService userService, FileService fileService) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping(path = "")
    public ResponseEntity<String> createRental(@Valid @ModelAttribute RentalDto rentalDTO, Authentication authentication) throws IOException {

        try {
            User user = this.userService.findUserByEmail(authentication.getName());

            String fileUrl = fileService.uploadFile(
                    rentalDTO.getPicture().getInputStream(),
                    rentalDTO.getPicture().getOriginalFilename(),
                    rentalDTO.getPicture().getContentType()
            );
            rentalDTO.setOwnerId(user.getId());
            Rental rental = this.rentalService.buildRental(rentalDTO);
            rental.setPicture(fileUrl);
            rentalService.save(rental);

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        return ResponseEntity.ok().body("message");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalResponseDto> getRental(@PathVariable("id") int id) {
        Rental rental = this.rentalService.findRentalById(id).orElse(null);
        RentalResponseDto rentalResponseDTO = rentalService.buildRentalResponseDTO(rental);

        if (rental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(rentalResponseDTO);
    }

    @GetMapping(path = "")
    public Iterable<RentalResponseDto> getAllRentals() {
        Iterable<RentalResponseDto> rentalResponseDtos = this.rentalService.findAllRentals();
        return ResponseEntity.ok().body(rentalResponseDtos).getBody();
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