# Task Management API — Java / Spring Boot

Java implementation of the same simple Task Management REST API used for the Python/FastAPI and C#/ASP.NET Core versions.

## Contract compatibility
The external JSON contract intentionally follows the Python version:
- `full_name`, `access_token`, `token_type`, `owner_id`, `created_at`, `updated_at`, `page_size`, `sort_by`, `sort_order`
- task `status` is a string: `TODO`, `IN_PROGRESS`, `DONE`
- task `priority` is a string: `LOW`, `MEDIUM`, `HIGH`

Internally, Java uses normal camelCase fields; Jackson exposes snake_case JSON.

## Stack
Java 17, Spring Boot 3.5.x, Spring Web, Spring Security, JWT, Spring Data JPA/Hibernate, PostgreSQL 17, Bean Validation, springdoc OpenAPI/Swagger UI, JUnit 5, Docker.

## Run with Docker
From the project root:

```powershell
docker compose up --build
```

Swagger UI: `http://localhost:8002/swagger-ui.html`
Health: `http://localhost:8002/health`

Stop without removing the database volume:

```powershell
docker compose down
```

## Main endpoints

Authentication:
- `POST /api/v1/auth/register`
- `POST /api/v1/auth/login`
- `GET /api/v1/auth/me`

Tasks:
- `POST /api/v1/tasks`
- `GET /api/v1/tasks`
- `GET /api/v1/tasks/{taskId}`
- `PATCH /api/v1/tasks/{taskId}`
- `DELETE /api/v1/tasks/{taskId}`

Query parameters: `page`, `page_size`, `status`, `priority`, `search`, `sort_by`, `sort_order`.

## Register
```json
{
  "email": "joseph@example.com",
  "full_name": "joseph",
  "password": "Password123!"
}
```

## Login
```json
{
  "email": "joseph@example.com",
  "password": "Password123!"
}
```

Swagger Authorize value:

```text
Bearer <access_token>
```

## Create task
```json
{
  "title": "Prepare for Interview",
  "description": "Prepare for Job Interview on all topics",
  "priority": "HIGH"
}
```

## Python / C# / Java mapping
| Concern | Python | C# | Java |
|---|---|---|---|
| REST | FastAPI | ASP.NET Core | Spring Boot |
| ORM | SQLAlchemy | Entity Framework Core | JPA/Hibernate |
| Validation | Pydantic | Data Annotations | Jakarta Validation |
| DI | FastAPI DI | .NET DI | Spring DI |
| Testing | pytest | xUnit | JUnit 5 |
| DB | PostgreSQL | PostgreSQL | PostgreSQL |
| Docs | OpenAPI/Swagger | OpenAPI/Swagger | OpenAPI/Swagger |
| Auth | JWT | JWT | JWT |
| Container | Docker | Docker | Docker |

This is a learning/interview project. For production use, replace development secrets, add Flyway/Liquibase migrations, strengthen JWT key management, add refresh-token/key rotation, CORS policy, rate limiting, and comprehensive integration/security tests.
