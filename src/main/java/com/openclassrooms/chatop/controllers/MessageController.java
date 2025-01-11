package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.response.GenericResponseDto;
import com.openclassrooms.chatop.dto.message.MessageDto;
import com.openclassrooms.chatop.entities.Message;
import com.openclassrooms.chatop.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("messages")
public class MessageController {

    private final MessageService messageService;

    MessageController(final MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(
            description = "Create message",
            tags = {"Message"}
    )
    @RequestBody(
            content = @Content(
                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                    schema = @Schema(implementation = MessageDto.class)
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "return success message",
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
    @PostMapping
    public ResponseEntity<GenericResponseDto> postMessage(@Valid @RequestBody MessageDto messageDto) {
        Message message = this.messageService.messageDtoToMessage(messageDto);
        this.messageService.saveMessage(message);
        return ResponseEntity.ok().body(new GenericResponseDto("Message send with success"));
    }

}
