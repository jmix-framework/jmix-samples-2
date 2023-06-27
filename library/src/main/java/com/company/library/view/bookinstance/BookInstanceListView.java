package com.company.library.view.bookinstance;

import com.company.library.entity.BookInstance;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "bookInstances", layout = MainView.class)
@ViewController("BookInstance.list")
@ViewDescriptor("book-instance-list-view.xml")
@LookupComponent("bookInstancesDataGrid")
@DialogMode(width = "50em")
public class BookInstanceListView extends StandardListView<BookInstance> {
}