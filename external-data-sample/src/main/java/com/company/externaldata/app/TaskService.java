package com.company.externaldata.app;

import com.company.externaldata.entity.Task;
import io.jmix.core.EntityStates;
import io.jmix.core.MetadataTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    public static final String TASKS_BASE_URL = "http://localhost:18080/tasks";
    private final EntityStates entityStates;

    @Autowired
    private RestTemplate restTemplate;

    public TaskService(EntityStates entityStates) {
        this.entityStates = entityStates;
    }

    private void setState(Task... tasks) {
        for (Task task : tasks) {
            entityStates.setNew(task, false);
        }
    }

    public List<Task> loadTasks() {
        log.info("Loading tasks");
        Task[] tasks = restTemplate.getForObject(TASKS_BASE_URL, Task[].class);
        setState(tasks);
        return Arrays.asList(tasks);
    }

    public Task loadTask(long id) {
        log.info("Loading task id={}", id);
        ResponseEntity<Task> response = restTemplate.getForEntity(TASKS_BASE_URL + "/"  + id, Task.class);
        Task task = response.getBody();
        if (task == null)
            throw new IllegalArgumentException("Not found");
        setState(task);
        return task;
    }

    public Task saveTask(Task task) {
        log.info("Saving task {}", task);
        String url = task.getId() != null ?
                TASKS_BASE_URL + "/"  + task.getId() :
                TASKS_BASE_URL;
        ResponseEntity<Task> response = restTemplate.postForEntity(url, task, Task.class);
        Task savedTask = Objects.requireNonNull(response.getBody());
        if (task.getId() == null) {
            // set new ID to the passed instance to let the framework match the saved instance with the original one
            task.setId(savedTask.getId());
        }
        // set not-new state to the DTO
        entityStates.setNew(savedTask, false);
        return savedTask;
    }

    public void deleteTask(Task task) {
        log.info("Deleting task {}", task);
        restTemplate.delete(TASKS_BASE_URL + "/" + task.getId());
    }
}