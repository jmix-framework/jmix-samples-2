package com.company.externaldata.app;

import com.company.externaldata.entity.Project;
import io.jmix.core.EntityStates;
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
public class ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

    public static final String PROJECTS_BASE_URL = "http://localhost:18080/projects";

    private final EntityStates entityStates;

    @Autowired
    private RestTemplate restTemplate;

    public ProjectService(EntityStates entityStates) {
        this.entityStates = entityStates;
    }

    private void setState(Project... projects) {
        for (Project project : projects) {
            entityStates.setNew(project, false);
        }
    }

    public List<Project> loadAll() {
        log.info("Load projects");
        Project[] projects = restTemplate.getForObject(PROJECTS_BASE_URL, Project[].class);
        setState(projects);
        return Arrays.asList(projects);
    }

    public Project loadProject(long id) {
        log.info("Loading project id={}", id);
        ResponseEntity<Project> response = restTemplate.getForEntity(PROJECTS_BASE_URL + "/"  + id, Project.class);
        Project project = response.getBody();
        if (project == null)
            throw new IllegalArgumentException("Not found");
        setState(project);
        return project;
    }

    public Project save(Project project) {
        log.info("Saving project {}", project);
        String url = project.getId() != null ?
                PROJECTS_BASE_URL + "/"  + project.getId() :
                PROJECTS_BASE_URL;
        ResponseEntity<Project> response = restTemplate.postForEntity(url, project, Project.class);
        Project savedProject = Objects.requireNonNull(response.getBody());
        if (project.getId() == null) {
            // set new ID to the passed instance to let the framework match the saved instance with the original one
            project.setId(savedProject.getId());
        }
        // set not-new state to the DTO
        setState(savedProject);
        return savedProject;
    }

    public void delete(Project project) {
        log.info("Deleting project {}", project);
        restTemplate.delete(PROJECTS_BASE_URL + "/" + project.getId());
    }
}