package com.openclassrooms.chatop.mappers.implementations;

public interface DtoMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);
}