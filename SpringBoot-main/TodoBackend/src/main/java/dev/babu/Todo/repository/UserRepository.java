package dev.babu.Todo.repository;

import dev.babu.Todo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// CRUD - Create Read Update Delete
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
