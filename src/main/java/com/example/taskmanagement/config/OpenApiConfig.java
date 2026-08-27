package com.example.taskmanagement.config;
import io.swagger.v3.oas.models.OpenAPI; import io.swagger.v3.oas.models.info.Info; import io.swagger.v3.oas.models.security.*; import org.springframework.context.annotation.*;
@Configuration public class OpenApiConfig{@Bean OpenAPI openAPI(){return new OpenAPI().info(new Info().title("Task Management API").version("v1")).addSecurityItem(new SecurityRequirement().addList("bearerAuth")).schemaRequirement("bearerAuth",new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT").name("Authorization"));}}
