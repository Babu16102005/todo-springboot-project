package dev.babu.Todo.controller;

import dev.babu.Todo.models.Todo;
import dev.babu.Todo.service.TodoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController    // This annotation is used to define a controller class in Spring Boot.
@RequestMapping("/api/v1/todo")    // This annotation is used to define a base URL for all the endpoints in this controller.
@Slf4j    // This annotation is used to enable logging in the class.
public class TodoController {
    @Autowired    // This annotation is used to inject the TodoService dependency.
    private TodoService todoService;

    // Path Variable
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Todo Retrieved Successfully"),
            @ApiResponse(responseCode = "404", description = "Todo was not found!")
    })
    @GetMapping("/{id}")    // This annotation is used to define a GET endpoint for the getTodoById method.
    ResponseEntity<Todo> getTodoById(@PathVariable long id) {
        try {
            Todo createdTodo = todoService.getTodoById(id);
            return new ResponseEntity<>(createdTodo, HttpStatus.OK);
        } catch (RuntimeException exception) {
            log.info("Error");
            log.warn("");
            log.error("", exception);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping    // This annotation is used to define a GET endpoint for the getTodos method.
    ResponseEntity<List<Todo>> getTodos() {
        return new ResponseEntity<List<Todo>>(todoService.getTodos(), HttpStatus.OK);
    }

    @GetMapping("/page")    // This annotation is used to define a GET endpoint for the getTodosPaged method.
    ResponseEntity<Page<Todo>> getTodosPaged(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(todoService.getAllTodosPages(page, size), HttpStatus.OK);
    }


    @PostMapping("/create")    // This annotation is used to define a POST endpoint for the createUser method.
    ResponseEntity<Todo> createUser(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping    // This annotation is used to define a PUT endpoint for the updateTodoById method.
    ResponseEntity<Todo> updateTodoById(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.updateTodo(todo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")    // This annotation is used to define a DELETE endpoint for the deleteTodoById method.
    void deleteTodoById(@PathVariable long id) {
        todoService.deleteTodoById(id);
    }
}
