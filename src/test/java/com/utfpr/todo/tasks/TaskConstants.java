package com.utfpr.todo.tasks;

import java.util.UUID;

public class TaskConstants {

    private static final String TITLE = "Test";
    private static final String DESCRIPTION = "Test";
    private static final boolean COMPLETED = false;
    private static final String PRIORITY = "low";


    

    public static final TaskModel TASK = TaskModel.builder()
    .title(TITLE)
    .description(DESCRIPTION)
    .completed(COMPLETED)
    .priority(PRIORITY)
    .build();

    public static final TaskModel TASK_CREATED = TaskModel.builder()
    .id(UUID.randomUUID())
    .title(TITLE)
    .description(DESCRIPTION)
    .completed(COMPLETED)
    .priority(PRIORITY)
    .build();
}
