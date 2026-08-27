package com.example.taskmanagement.model;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="tasks")
public class TaskItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=200) private String title;
 @Column(length=2000) private String description;
 @Column(nullable=false,length=30) private String status="TODO";
 @Column(nullable=false,length=30) private String priority="MEDIUM";
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="owner_id",nullable=false) private User owner;
 @Column(name="created_at",nullable=false) private Instant createdAt=Instant.now();
 @Column(name="updated_at",nullable=false) private Instant updatedAt=Instant.now();
 @PreUpdate void touch(){updatedAt=Instant.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public String getDescription(){return description;} public String getStatus(){return status;} public String getPriority(){return priority;} public User getOwner(){return owner;} public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
 public void setTitle(String v){title=v;} public void setDescription(String v){description=v;} public void setStatus(String v){status=v;} public void setPriority(String v){priority=v;} public void setOwner(User v){owner=v;}
}
