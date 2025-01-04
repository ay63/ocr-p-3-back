package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.entities.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface RentalRepository extends CrudRepository<Rental, Integer> {
}
