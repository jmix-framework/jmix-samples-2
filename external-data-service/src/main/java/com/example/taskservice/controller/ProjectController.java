package com.example.taskservice.controller;

import com.example.taskservice.entity.Project;
import com.example.taskservice.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    private ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/projects")
    public List<Project> all() {
        return projectRepository.findAll();
    }

    @PostMapping("/projects")
    public Project save(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PostMapping("/projects/{id}")
    public Project update(@RequestBody Project newProject, @PathVariable Long id) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(newProject.getName());
                    project.setDescription(newProject.getDescription());
                    return projectRepository.save(project);
                })
                .orElseGet(() -> {
                    newProject.setId(id);
                    return projectRepository.save(newProject);
                });
    }

    @DeleteMapping("/projects/{id}")
    public void delete(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }
}
