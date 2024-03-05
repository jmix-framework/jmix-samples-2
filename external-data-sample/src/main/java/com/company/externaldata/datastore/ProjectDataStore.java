package com.company.externaldata.datastore;

import com.company.externaldata.app.ProjectService;
import com.company.externaldata.entity.Project;
import io.jmix.core.DataStore;
import io.jmix.core.LoadContext;
import io.jmix.core.SaveContext;
import io.jmix.core.ValueLoadContext;
import io.jmix.core.entity.KeyValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("sample_ProjectDataStore")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ProjectDataStore implements DataStore {

    @Autowired
    private ProjectService projectService;

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    @Override
    public Object load(LoadContext<?> context) {
        return projectService.loadProject((Long) context.getId());
    }

    @Override
    public List<Object> loadList(LoadContext<?> context) {
        return new ArrayList<>(projectService.loadAll());
    }

    @Override
    public long getCount(LoadContext<?> context) {
        return 0;
    }

    @Override
    public Set<?> save(SaveContext context) {
        Set<Project> savedEntities = context.getEntitiesToSave().stream()
                .map(entity -> projectService.save((Project) entity))
                .collect(Collectors.toSet());
        context.getEntitiesToRemove()
                .forEach(entity -> projectService.delete((Project) entity));
        return savedEntities;
    }

    @Override
    public List<KeyValueEntity> loadValues(ValueLoadContext context) {
        return null;
    }

    @Override
    public long getCount(ValueLoadContext context) {
        return 0;
    }
}
