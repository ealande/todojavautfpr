package com.utfpr.todo.tasks;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTask_WithDataValid_ReturnsCreated() throws Exception {
        Mockito.when(taskService.create(TaskConstants.TASK)).thenReturn(TaskConstants.TASK_CREATED);
    
        mockMvc.perform(
            post("/tasks")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(TaskConstants.TASK)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(TaskConstants.TASK_CREATED)));
    }
}
