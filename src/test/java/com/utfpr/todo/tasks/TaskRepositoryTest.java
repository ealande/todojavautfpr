package com.utfpr.todo.tasks;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class TaskRepositoryTest {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  public void createTask_WithDataValid_ReturnsTask() {

    TaskModel createdTask = taskRepository.save(TaskConstants.TASK);

    System.out.println("createdTask");
    System.out.println(createdTask);

    TaskModel foundTask = testEntityManager.find(TaskModel.class, createdTask.getId());

    System.out.println("foundTask");
    System.out.println(foundTask);

    Assertions.assertThat(createdTask).isEqualTo(foundTask);

  }

  @Test
  public void createTask_WithInvalidData_ThrowsException() {

    TaskModel emptyTask = TaskConstants.TASK_EMPTY;

    Assertions.assertThatThrownBy(() -> taskRepository.save(emptyTask)).isInstanceOf(RuntimeException.class);

  }

}