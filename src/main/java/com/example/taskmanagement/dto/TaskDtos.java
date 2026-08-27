package com.example.taskmanagement.dto;
import jakarta.validation.constraints.*; import java.time.Instant; import java.util.List;
public final class TaskDtos { private TaskDtos(){}
 public record CreateTaskRequest(@NotBlank @Size(max=200) String title,@Size(max=2000) String description,String priority){ public CreateTaskRequest{ if(priority==null||priority.isBlank()) priority="MEDIUM"; } }
 public record UpdateTaskRequest(@Size(max=200) String title,@Size(max=2000) String description,String status,String priority){}
 public record TaskResponse(Long id,String title,String description,String status,String priority,Long ownerId,Instant createdAt,Instant updatedAt){}
 public record PagedResult<T>(List<T> items,int page,int pageSize,long total,int pages){}
}
