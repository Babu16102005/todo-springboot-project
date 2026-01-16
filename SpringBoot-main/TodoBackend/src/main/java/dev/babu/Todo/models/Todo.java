package dev.babu.Todo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue
    Long id;
    @NotNull
    @NotBlank
    @Schema(name = "title", example = "Complete Spring Boot")
    String title;
    @NotNull
    @NotBlank
    String description;
    Boolean isCompleted;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

// In a Spring Boot backend, the model file is used to represent your data structure and map it to the database.