package com.company.library.view.librarydepartment;

import com.company.library.entity.LibraryDepartment;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "libraryDepartments/:id", layout = MainView.class)
@ViewController("LibraryDepartment.detail")
@ViewDescriptor("library-department-detail-view.xml")
@EditedEntityContainer("libraryDepartmentDc")
public class LibraryDepartmentDetailView extends StandardDetailView<LibraryDepartment> {
}