package com.example.taskservice.controller;

import com.example.taskservice.entity.Task;
import com.example.taskservice.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public List<Task> all() {
        return taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task save(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PostMapping("/tasks/{id}")
    public Task update(@RequestBody Task newTask, @PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setName(newTask.getName());
                    task.setStartDate(newTask.getStartDate());
                    task.setEndDate(newTask.getEndDate());
                    task.setCompleted(newTask.getCompleted());
                    return taskRepository.save(task);
                })
                .orElseGet(() -> {
                    newTask.setId(id);
                    return taskRepository.save(newTask);
                });
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
