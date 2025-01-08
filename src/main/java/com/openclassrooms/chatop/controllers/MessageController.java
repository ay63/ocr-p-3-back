package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.user.MessageDto;
import com.openclassrooms.chatop.entities.Message;
import com.openclassrooms.chatop.exceptions.BadRequestException;
import com.openclassrooms.chatop.services.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController()
@RequestMapping("messages")
public class MessageController {

    private final MessageService messageService;

    MessageController(final MessageService messageService) {
        this.messageService = messageService;
    }


    @PostMapping(path = "")
    public ResponseEntity<Map<String, String>> postMessage(@Valid @RequestBody MessageDto messageDto) {
        Message message = this.messageService.buildMessageFromDto(messageDto);

        if (message == null)  throw new BadRequestException();

        this.messageService.saveMessage(message);
        return ResponseEntity.ok().body(Map.of("message", "Message send with success"));
    }

}
