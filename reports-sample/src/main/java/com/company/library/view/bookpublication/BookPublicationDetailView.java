package com.company.library.view.bookpublication;

import com.company.library.entity.BookPublication;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "bookPublications/:id", layout = MainView.class)
@ViewController("BookPublication.detail")
@ViewDescriptor("book-publication-detail-view.xml")
@EditedEntityContainer("bookPublicationDc")
public class BookPublicationDetailView extends StandardDetailView<BookPublication> {
}