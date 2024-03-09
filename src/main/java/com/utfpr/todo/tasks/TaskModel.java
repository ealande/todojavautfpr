package com.utfpr.todo.tasks;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tbl-tasks")
public class TaskModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String title;

  private String description;

  private boolean completed;

  private LocalDateTime createdAt;

  private LocalDateTime endAt;

  private String priority;

}