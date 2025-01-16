package com.openclassrooms.chatop.services.implementation;

import com.openclassrooms.chatop.dto.message.MessageDto;
import com.openclassrooms.chatop.entities.Message;
import com.openclassrooms.chatop.mappers.implementations.message.MessageDtoMapperImpl;
import com.openclassrooms.chatop.repositories.MessageRepository;
import com.openclassrooms.chatop.services.MessageService;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService {


    private final MessageRepository messageRepository;
    private final MessageDtoMapperImpl messageDtoMapperImpl;

    MessageServiceImpl(MessageRepository messageRepository,
                   MessageDtoMapperImpl messageDtoMapperImpl
    ) {
        this.messageRepository = messageRepository;
        this.messageDtoMapperImpl = messageDtoMapperImpl;
    }

    @Override
    public void saveMessage(Message message) {
        this.messageRepository.save(message);
    }

    @Override
    public Message messageDtoToMessage(MessageDto messageDto) {
        return this.messageDtoMapperImpl.toEntity(messageDto);
    }

}
