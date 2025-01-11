package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.rental.RentalsResponseDto;
import com.openclassrooms.chatop.dto.response.GenericResponseDto;
import com.openclassrooms.chatop.dto.rental.RentalCreateDto;
import com.openclassrooms.chatop.dto.rental.RentalResponseDto;
import com.openclassrooms.chatop.dto.rental.RentalUpdateDto;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.RentalService;
import com.openclassrooms.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
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

    @Operation(
            description = "Create new rental",
            tags = {"Rentals"}
    )
    @RequestBody(
            content = @Content(
                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                    schema = @Schema(implementation = RentalCreateDto.class)
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Rental created !",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            )
    })
    @PostMapping
    public ResponseEntity<GenericResponseDto> createRental(
            @Valid @ModelAttribute RentalCreateDto rentalDTO,
            Authentication authentication
    ) throws Exception {
        User user = this.userService.findUserByEmailOrThrowError(authentication.getName());
        Rental rental = this.rentalService.createRentalWithFileUpload(user, rentalDTO);
        rentalService.saveRental(rental);
        return ResponseEntity.ok().body(new GenericResponseDto("Rental created !"));
    }

    @Operation(
            description = "Get rental with id",
            tags = {"Rentals"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "return one rental",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RentalResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            )
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalResponseDto> getRental(@PathVariable("id") int id) {
        Rental rental = this.rentalService.findRentalByUserIdOrThrowError(id);
        return ResponseEntity.ok().body(rentalService.rentalToRentalResponseDto(rental));
    }

    @Operation(
            description = "Get all rentals",
            tags = {"Rentals"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "return list of rentals",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RentalsResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "unauthorized"
            )
    })
    @GetMapping()
    public ResponseEntity<RentalsResponseDto> getAllRentals() {
        return ResponseEntity.ok().body(this.rentalService.getAllRentalsResponse());
    }


    @Operation(
            description = "Update rental",
            tags = {"Rentals"}
    )
    @RequestBody(
            content = @Content(
                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                    schema = @Schema(implementation = RentalUpdateDto.class)
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "updated rental message success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "unauthorized"
            )
    })
    @PutMapping(path = "/{id}")
    public ResponseEntity<GenericResponseDto> updateRental(
            @PathVariable("id") int id,
            @Valid @ModelAttribute RentalUpdateDto rentalUpdateDTO
    ) {
        Rental rental = this.rentalService.findRentalByUserIdOrThrowError(id);
        this.rentalService.updateAndSaveRental(rental, rentalUpdateDTO);
        return ResponseEntity.ok().body(new GenericResponseDto("Rental updated !"));
    }

}