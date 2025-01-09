package com.openclassrooms.chatop.mapper.implementation;

public interface DtoMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);
}