package com.openclassrooms.chatop.services;


import com.openclassrooms.chatop.dto.message.MessageDto;
import com.openclassrooms.chatop.entities.Message;

public interface MessageService {

    void saveMessage(Message message);

    Message messageDtoToMessage(MessageDto messageDto);

}
