package com.openclassrooms.chatop.mapper.implementation.message;

import com.openclassrooms.chatop.mapper.implementation.DtoMapper;
import com.openclassrooms.chatop.dto.message.MessageDto;
import com.openclassrooms.chatop.entities.Message;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.RentalRepository;
import com.openclassrooms.chatop.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class MessageDtoMapperImpl implements DtoMapper<Message, MessageDto> {

    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    MessageDtoMapperImpl(UserRepository userRepository, RentalRepository rentalRepository) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Message toEntity(MessageDto dto) {

        User user = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new EntityNotFoundException(String.format("User with ID %d not found", dto.getUserId()))
        );

        Rental rental = rentalRepository.findById(dto.getRentalId()).orElseThrow(
                () -> new EntityNotFoundException(String.format("Rental with ID %d not found", dto.getRentalId()))
        );

        Message message = new Message();
        message.setMessage(dto.getMessage());
        message.setUser(user);
        message.setRental(rental);
        message.setCreatedAt(dto.getCreatedAt());
        message.setUpdatedAt(dto.getUpdatedAt());
        return message;
    }

    @Override
    public MessageDto toDto(Message entity) {
        MessageDto dto = new MessageDto();
        dto.setMessage(entity.getMessage());
        dto.setUserId(entity.getUser().getId());
        dto.setRentalId(entity.getRental().getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}