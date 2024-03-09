package com.utfpr.todo.tasks;

import org.assertj.core.api.Assertions;
import org.h2.util.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TaskIT {
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createTask_WithDataValid_ReturnsCreated() {
        ResponseEntity<TaskModel> response = restTemplate.postForEntity("/tasks", TaskConstants.TASK,TaskModel.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response.getBody().getId()).isNotNull();
        Assertions.assertThat(response.getBody().getTitle()).isEqualTo(TaskConstants.TASK_CREATED.getTitle());
        Assertions.assertThat(response.getBody().getDescription()).isEqualTo(TaskConstants.TASK_CREATED.getDescription());
        Assertions.assertThat(response.getBody().getPriority()).isEqualTo(TaskConstants.TASK_CREATED.getPriority());

    }
}
