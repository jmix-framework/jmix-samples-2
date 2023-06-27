package com.company.externaldata.view.project;

import com.company.externaldata.entity.Project;
import com.company.externaldata.view.main.MainView;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "project/detail", layout = MainView.class)
@ViewController("Project.detail")
@ViewDescriptor("project-detail-view.xml")
@EditedEntityContainer("projectDc")
public class ProjectDetailView extends StandardDetailView<Project> {

    @Override
    protected void findEntityId(BeforeEnterEvent event) {
        // Because DTO entity cannot be loaded by Id, we need to prevent Id parsing from route parameters
    }
}
