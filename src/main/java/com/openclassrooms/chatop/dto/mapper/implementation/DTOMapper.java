package com.openclassrooms.chatop.dto.mapper.implementation;

public interface DTOMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);
}