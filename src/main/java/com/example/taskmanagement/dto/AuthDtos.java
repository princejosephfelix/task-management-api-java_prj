package com.example.taskmanagement.dto;
import jakarta.validation.constraints.*;
public final class AuthDtos { private AuthDtos(){}
 public record RegisterRequest(@NotBlank @Email @Size(max=320) String email,@NotBlank @Size(max=150) String fullName,@NotBlank @Size(min=8,max=100) String password){}
 public record LoginRequest(@NotBlank @Email String email,@NotBlank String password){}
 public record UserResponse(Long id,String email,String fullName,String role){}
 public record TokenResponse(String accessToken,String tokenType){}
}
