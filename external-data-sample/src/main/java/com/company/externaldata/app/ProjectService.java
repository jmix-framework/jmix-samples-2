package com.company.externaldata.app;

import com.company.externaldata.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ProjectService {

    public static final String PROJECTS_BASE_URL = "http://localhost:18080/projects";

    @Autowired
    private RestTemplate restTemplate;

    public List<Project> loadAll() {
        Project[] tasks = restTemplate.getForObject(PROJECTS_BASE_URL, Project[].class);
        return Arrays.asList(tasks);
    }

    public Project save(Project project) {
        String url = project.getId() != null ?
                PROJECTS_BASE_URL + "/"  + project.getId() :
                PROJECTS_BASE_URL;
        ResponseEntity<Project> response = restTemplate.postForEntity(url, project, Project.class);
        return response.getBody();
    }

    public void delete(Project project) {
        restTemplate.delete(PROJECTS_BASE_URL + "/" + project.getId());
    }
}