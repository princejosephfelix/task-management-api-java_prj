package com.example.taskmanagement.service;
import com.example.taskmanagement.dto.AuthDtos.*; import com.example.taskmanagement.model.User; import com.example.taskmanagement.repository.UserRepository; import com.example.taskmanagement.security.JwtService; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service;
@Service public class AuthService{
 private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthService(UserRepository u,PasswordEncoder e,JwtService j){users=u;encoder=e;jwt=j;}
 public UserResponse register(RegisterRequest r){if(users.existsByEmailIgnoreCase(r.email()))throw new IllegalArgumentException("Email already registered.");User u=new User();u.setEmail(r.email().trim().toLowerCase());u.setFullName(r.fullName().trim());u.setPasswordHash(encoder.encode(r.password()));u.setRole("USER");u=users.save(u);return response(u);}
 public TokenResponse login(LoginRequest r){User u=users.findByEmailIgnoreCase(r.email()).orElseThrow(()->new IllegalArgumentException("Invalid email or password."));if(!encoder.matches(r.password(),u.getPasswordHash()))throw new IllegalArgumentException("Invalid email or password.");return new TokenResponse(jwt.create(u.getId(),u.getEmail(),u.getRole(),u.getFullName()),"bearer");}
 public UserResponse me(Long id){return response(users.findById(id).orElseThrow(()->new IllegalArgumentException("User not found.")));}
 private UserResponse response(User u){return new UserResponse(u.getId(),u.getEmail(),u.getFullName(),u.getRole());}
}
