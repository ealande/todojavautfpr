package com.utfpr.todo.tasks;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository
    extends CrudRepository<TaskModel, UUID> {

}