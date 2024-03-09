package com.utfpr.todo.tasks;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    //Qual_operação_está_sendo_executada?
    @Test
    public void CreateTask_WithValidData_ReturnsTask(){

        Mockito.when(taskRepository.save(TaskConstants.TASK)).thenReturn(TaskConstants.TASK_CREATED);

        TaskModel createdTask = taskService.create(TaskConstants.TASK);

        Assertions.assertThat(createdTask).isNotNull();
        Assertions.assertThat(createdTask.getTitle()).isEqualTo(TaskConstants.TASK.getTitle());
        Assertions.assertThat(createdTask.getDescription()).isEqualTo(TaskConstants.TASK.getDescription());
        Assertions.assertThat(createdTask.getPriority()).isEqualTo(TaskConstants.TASK.getPriority());


    }

}
