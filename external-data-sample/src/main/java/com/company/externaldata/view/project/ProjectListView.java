package com.company.externaldata.view.project;

import com.company.externaldata.entity.Project;
import com.company.externaldata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;

import java.util.Collections;
import java.util.List;

@Route(value = "projects", layout = MainView.class)
@ViewController("Project.list")
@ViewDescriptor("project-list-view.xml")
@LookupComponent("projectsDataGrid")
@DialogMode(width = "50em")
public class ProjectListView extends StandardListView<Project> {
}
