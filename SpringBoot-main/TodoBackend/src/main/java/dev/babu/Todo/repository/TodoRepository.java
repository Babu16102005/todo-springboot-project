package dev.babu.Todo.repository;

import dev.babu.Todo.models.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// CRUD - Create Read Update Delete
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserEmail(String email);
    Page<Todo> findByUserEmail(String email, Pageable pageable);
}
