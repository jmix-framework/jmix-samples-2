package com.company.library.view.librarydepartment;

import com.company.library.entity.LibraryDepartment;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "libraryDepartments", layout = MainView.class)
@ViewController("LibraryDepartment.list")
@ViewDescriptor("library-department-list-view.xml")
@LookupComponent("libraryDepartmentsDataGrid")
@DialogMode(width = "50em")
public class LibraryDepartmentListView extends StandardListView<LibraryDepartment> {
}