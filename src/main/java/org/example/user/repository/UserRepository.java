package org.example.user.repository;

import org.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByName(String name);   // if you still want it
    Optional<User> findByEmail(String email); // <-- needed for compile
}
