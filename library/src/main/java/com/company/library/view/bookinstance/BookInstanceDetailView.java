package com.company.library.view.bookinstance;

import com.company.library.entity.BookInstance;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "bookInstances/:id", layout = MainView.class)
@ViewController("BookInstance.detail")
@ViewDescriptor("book-instance-detail-view.xml")
@EditedEntityContainer("bookInstanceDc")
public class BookInstanceDetailView extends StandardDetailView<BookInstance> {
}