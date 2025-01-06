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
import java.util.HashMap;
import java.util.List;
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
    public ResponseEntity<Map<String, String>> createRental(@Valid @ModelAttribute RentalDto rentalDTO, Authentication authentication) throws Exception {

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

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok().body(Map.of("message", "Rental created !"));
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
    public ResponseEntity<Map<String, List<RentalResponseDto>>> getAllRentals() {
        List<RentalResponseDto> rentalResponseDtos = this.rentalService.findAllRentals();

        Map<String, List<RentalResponseDto>> response = new HashMap<>();
        response.put("rentals", rentalResponseDtos);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> updateRental(@PathVariable("id") int id, @Valid @ModelAttribute RentalUpdateDto rentalUpdateDTO) {
        Rental rental = this.rentalService.findRentalById(id).orElse(null);
        if (rental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        this.rentalService.updateRental(rental, rentalUpdateDTO);

        return ResponseEntity.ok().body(Map.of("message", "Rental updated !"));
    }

}