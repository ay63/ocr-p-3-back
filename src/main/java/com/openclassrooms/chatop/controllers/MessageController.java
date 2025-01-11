package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.response.GenericResponseDto;
import com.openclassrooms.chatop.dto.message.MessageDto;
import com.openclassrooms.chatop.entities.Message;
import com.openclassrooms.chatop.services.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("messages")
public class MessageController {

    private final MessageService messageService;

    MessageController(final MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(path = "")
    public ResponseEntity<GenericResponseDto> postMessage(@Valid @RequestBody MessageDto messageDto) {
        Message message = this.messageService.messageDtoToMessage(messageDto);
        this.messageService.saveMessage(message);
        return ResponseEntity.ok().body(new GenericResponseDto("Message send with success"));
    }

}
