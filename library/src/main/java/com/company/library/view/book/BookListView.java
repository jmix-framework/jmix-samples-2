package com.company.library.view.book;

import com.company.library.entity.Book;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "books", layout = MainView.class)
@ViewController("Book.list")
@ViewDescriptor("book-list-view.xml")
@LookupComponent("booksDataGrid")
@DialogMode(width = "50em")
public class BookListView extends StandardListView<Book> {
}