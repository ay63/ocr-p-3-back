package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository()
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}
