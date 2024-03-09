package com.utfpr.todo.tasks;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
    
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    public TaskModel create(TaskModel task) {
        return taskRepository.save(task);
    }

    public TaskModel complete(UUID id) {
        TaskModel task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("task not found"));
        task.setCompleted(true);
        return taskRepository.save(task);
    }

}
