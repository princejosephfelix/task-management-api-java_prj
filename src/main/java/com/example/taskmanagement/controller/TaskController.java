package com.example.taskmanagement.controller;
import com.example.taskmanagement.dto.TaskDtos.*; import com.example.taskmanagement.service.TaskService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/tasks") public class TaskController{
 private final TaskService service; public TaskController(TaskService s){service=s;}
 @PostMapping public ResponseEntity<TaskResponse> create(Authentication a,@Valid @RequestBody CreateTaskRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(uid(a),r));}
 @GetMapping public PagedResult<TaskResponse> list(Authentication a,@RequestParam(defaultValue="1")int page,@RequestParam(name="page_size",defaultValue="10")int pageSize,@RequestParam(required=false)String status,@RequestParam(required=false)String priority,@RequestParam(required=false)String search,@RequestParam(name="sort_by",defaultValue="created_at")String sortBy,@RequestParam(name="sort_order",defaultValue="desc")String sortOrder){return service.list(uid(a),admin(a),page,pageSize,status,priority,search,sortBy,sortOrder);}
 @GetMapping("/{taskId}") public ResponseEntity<TaskResponse> get(Authentication a,@PathVariable Long taskId){var x=service.get(taskId,uid(a),admin(a));return x==null?ResponseEntity.notFound().build():ResponseEntity.ok(x);}
 @PatchMapping("/{taskId}") public ResponseEntity<TaskResponse> update(Authentication a,@PathVariable Long taskId,@Valid @RequestBody UpdateTaskRequest r){var x=service.update(taskId,uid(a),admin(a),r);return x==null?ResponseEntity.notFound().build():ResponseEntity.ok(x);}
 @DeleteMapping("/{taskId}") public ResponseEntity<Void> delete(Authentication a,@PathVariable Long taskId){return service.delete(taskId,uid(a),admin(a))?ResponseEntity.noContent().build():ResponseEntity.notFound().build();}
 private Long uid(Authentication a){return Long.valueOf(a.getName());} private boolean admin(Authentication a){return a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_ADMIN"));}
}
