package dev.babu.Todo.controller;

import dev.babu.Todo.models.User;
import dev.babu.Todo.repository.UserRepository;
import dev.babu.Todo.service.UserService;
import dev.babu.Todo.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController    // This annotation is used to define a controller class in Spring Boot.
@RequestMapping("/auth")    // This annotation is used to define a base URL for all the endpoints in this controller.
@RequiredArgsConstructor    // This annotation is used to generate a constructor for the class.
public class AuthController {

    private final UserRepository userRepo;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")    // This annotation is used to define a POST endpoint for the register method.
    public ResponseEntity<String> register(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = passwordEncoder.encode(body.get("password"));
        if (userRepo.findByEmail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        userService.createUser(User.builder().email(email).password(password).build());
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")    // This annotation is used to define a POST endpoint for the login method.
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        var userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email");
        }

        var user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));
    }
}

