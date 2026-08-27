package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.TaskItem;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<TaskItem, Long> {

    @Query("""
        select t from TaskItem t
        where (:admin = true or t.owner.id = :ownerId)
          and (:status is null or t.status = :status)
          and (:priority is null or t.priority = :priority)
          and (
              coalesce(:search, '') = ''
              or lower(t.title) like lower(concat('%', :search, '%'))
              or lower(coalesce(t.description, '')) like lower(concat('%', :search, '%'))
          )
        """)
    Page<TaskItem> search(
        @Param("ownerId") Long ownerId,
        @Param("admin") boolean admin,
        @Param("status") String status,
        @Param("priority") String priority,
        @Param("search") String search,
        Pageable pageable);
}