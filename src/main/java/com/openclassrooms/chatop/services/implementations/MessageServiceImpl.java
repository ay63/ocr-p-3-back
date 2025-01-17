package com.openclassrooms.chatop.services.implementations;

import com.openclassrooms.chatop.dto.message.MessageDto;
import com.openclassrooms.chatop.entities.Message;
import com.openclassrooms.chatop.mappers.implementations.MessageDtoMapper;
import com.openclassrooms.chatop.repositories.MessageRepository;
import com.openclassrooms.chatop.services.MessageService;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageDtoMapper messageDtoMapper;

    MessageServiceImpl(MessageRepository messageRepository,
                       MessageDtoMapper messageDtoMapper

    ) {
        this.messageRepository = messageRepository;
        this.messageDtoMapper = messageDtoMapper;
    }

    @Override
    public void saveMessage(Message message) {
        this.messageRepository.save(message);
    }

    @Override
    public Message messageDtoToMessage(MessageDto messageDto) {
        return this.messageDtoMapper
                .toEntity(messageDto);
    }

}
