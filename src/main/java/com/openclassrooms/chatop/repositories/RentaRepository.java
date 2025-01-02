package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.entities.Rental;
import org.springframework.data.repository.CrudRepository;

public interface RentaRepository extends CrudRepository<Rental, Integer> {
}
