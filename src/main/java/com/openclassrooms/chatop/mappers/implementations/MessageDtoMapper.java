package com.openclassrooms.chatop.mappers.implementations;

import com.openclassrooms.chatop.dto.message.MessageDto;
import com.openclassrooms.chatop.entities.Message;

public interface MessageDtoMapper extends DtoMapper<Message, MessageDto> {

    Message toEntity(MessageDto dto);

    MessageDto toDto(Message entity);

}
