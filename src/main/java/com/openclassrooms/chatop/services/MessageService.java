package com.openclassrooms.chatop.services;


import com.openclassrooms.chatop.dto.mapper.implementation.message.MessageDtoMapperImpl;
import com.openclassrooms.chatop.dto.user.MessageDto;
import com.openclassrooms.chatop.entities.Message;
import com.openclassrooms.chatop.repositories.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageDtoMapperImpl messageDtoMapperImpl;

    MessageService(MessageRepository messageRepository, MessageDtoMapperImpl messageDtoMapperImpl){
        this.messageRepository = messageRepository;
        this.messageDtoMapperImpl = messageDtoMapperImpl;
    }

    /**
     * Save message object
     * @param message Message
     */
    public void saveMessage(Message message) {
        this.messageRepository.save(message);
    }


    /**
     * Build Message object from MessageDto
     * @param messageDto MessageDto
     * @return Message
     */
    public Message buildMessageFromDto(MessageDto messageDto) {
        return this.messageDtoMapperImpl.toEntity(messageDto);
    }

}
