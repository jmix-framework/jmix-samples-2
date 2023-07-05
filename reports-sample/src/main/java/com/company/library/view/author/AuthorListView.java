package com.company.library.view.author;

import com.company.library.entity.Author;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "authors", layout = MainView.class)
@ViewController("Author.list")
@ViewDescriptor("author-list-view.xml")
@LookupComponent("authorsDataGrid")
@DialogMode(width = "50em")
public class AuthorListView extends StandardListView<Author> {
}