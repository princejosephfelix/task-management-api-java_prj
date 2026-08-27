package com.example.taskmanagement.model;
import jakarta.persistence.*;
@Entity @Table(name="users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=320) private String email;
 @Column(name="full_name",nullable=false,length=150) private String fullName;
 @Column(name="password_hash",nullable=false) private String passwordHash;
 @Column(nullable=false,length=20) private String role="USER";
 public Long getId(){return id;} public String getEmail(){return email;} public String getFullName(){return fullName;} public String getPasswordHash(){return passwordHash;} public String getRole(){return role;}
 public void setEmail(String v){email=v;} public void setFullName(String v){fullName=v;} public void setPasswordHash(String v){passwordHash=v;} public void setRole(String v){role=v;}
}
