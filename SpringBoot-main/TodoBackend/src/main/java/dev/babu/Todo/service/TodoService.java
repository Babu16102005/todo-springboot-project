package dev.babu.Todo.service;

import dev.babu.Todo.repository.TodoRepository;
import dev.babu.Todo.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private dev.babu.Todo.repository.UserRepository userRepository;

    public Todo createTodo(Todo todo, String email) {
        dev.babu.Todo.models.User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public Todo getTodoById(Long id, String email) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        if (!todo.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized access to this todo");
        }
        return todo;
    }

    public Page<Todo> getAllTodosPages(int page, int size, String email) {
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findByUserEmail(email, pageable);
    }

    public List<Todo> getTodos(String email) {
        return todoRepository.findByUserEmail(email);
    }

    public Todo updateTodo(Todo todo, String email) {
        // Ensure the todo belongs to the user before updating
        Todo existingTodo = getTodoById(todo.getId(), email);
        todo.setUser(existingTodo.getUser()); // Keep the original user
        return todoRepository.save(todo);
    }

    public void deleteTodoById(Long id, String email) {
         Todo todo = getTodoById(id, email);
         todoRepository.delete(todo);
    }
}

