package com.example.taskmanagement.controller;
import com.example.taskmanagement.dto.AuthDtos.*; import com.example.taskmanagement.service.AuthService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/auth") public class AuthController{
 private final AuthService service; public AuthController(AuthService s){service=s;}
 @PostMapping("/register") public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.register(r));}
 @PostMapping("/login") public TokenResponse login(@Valid @RequestBody LoginRequest r){return service.login(r);}
 @GetMapping("/me") public UserResponse me(Authentication a){return service.me(Long.valueOf(a.getName()));}
}
