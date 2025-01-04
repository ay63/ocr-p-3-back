package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dto.user.MessageDto;
import com.openclassrooms.chatop.entities.Message;
import com.openclassrooms.chatop.services.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("messages")
public class MessageController {

    private final MessageService messageService;

    MessageController(final MessageService messageService) {
        this.messageService = messageService;
    }


    @PostMapping(path = "")
    public ResponseEntity<Map<String, String>> postMessage(@Valid @RequestBody MessageDto messageDto) {
        Message message = this.messageService.buildMessageFromDto(messageDto);

        if (message == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.messageService.saveMessage(message);

        return ResponseEntity.ok().body(Map.of("message", "Message send with success"));
    }

}
