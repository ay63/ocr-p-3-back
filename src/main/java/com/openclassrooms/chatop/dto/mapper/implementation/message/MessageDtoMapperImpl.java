package com.openclassrooms.chatop.dto.mapper.implementation.message;

import com.openclassrooms.chatop.dto.mapper.implementation.DTOMapper;
import com.openclassrooms.chatop.dto.user.MessageDto;
import com.openclassrooms.chatop.entities.Message;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.RentalRepository;
import com.openclassrooms.chatop.repositories.UserRepository;
import org.springframework.stereotype.Component;


@Component
public class MessageDtoMapperImpl implements DTOMapper<Message, MessageDto> {

    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    MessageDtoMapperImpl(UserRepository userRepository, RentalRepository rentalRepository) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Message toEntity(MessageDto dto) {

        User user = userRepository.findById(dto.getUserId()).orElse(null);
        Rental rental = rentalRepository.findById(dto.getRentalId()).orElse(null);

        if (user == null || rental == null) {
            return null;
        }

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
