package com.company.library.view.bookpublication;

import com.company.library.entity.BookPublication;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "bookPublications", layout = MainView.class)
@ViewController("BookPublication.list")
@ViewDescriptor("book-publication-list-view.xml")
@LookupComponent("bookPublicationsDataGrid")
@DialogMode(width = "50em")
public class BookPublicationListView extends StandardListView<BookPublication> {
}