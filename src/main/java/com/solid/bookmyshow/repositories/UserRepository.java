package com.solid.bookmyshow.repositories;

import com.solid.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByEmail(String email);

    @Override
    User save(User entity); //if user exists, it will update the user, otherwise it will create a new user
}
