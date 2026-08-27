package com.example.taskmanagement.controller;
import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestControllerAdvice public class ApiExceptionHandler{
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException e){Map<String,String> errors=new LinkedHashMap<>();e.getBindingResult().getFieldErrors().forEach(x->errors.put(x.getField(),x.getDefaultMessage()));return ResponseEntity.badRequest().body(Map.of("title","One or more validation errors occurred.","status",400,"errors",errors));}
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<Map<String,Object>> bad(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("title","Bad Request","status",400,"detail",e.getMessage()));}
}
