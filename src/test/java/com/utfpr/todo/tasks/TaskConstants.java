package com.utfpr.todo.tasks;

import java.util.UUID;

public class TaskConstants {

    private static final String TITLE = "Test";
    private static final String DESCRIPTION = "Test";
    private static final Boolean COMPLETED =  false;
    private static final String PRIORITY = "Low";


    

    public static final TaskModel TASK = TaskModel.builder()
            .title("Test")
            .description("Test")
            .completed(false)
            .priority("Low")
            .build();


    public static final TaskModel TASK_CREATED = TaskModel.builder()
    .id(UUID.randomUUID())
    .title(TITLE)
    .description(DESCRIPTION)
    .completed(COMPLETED)
    .priority(PRIORITY)
    .build();
}
