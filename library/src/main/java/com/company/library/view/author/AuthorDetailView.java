package com.company.library.view.author;

import com.company.library.entity.Author;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "authors/:id", layout = MainView.class)
@ViewController("Author.detail")
@ViewDescriptor("author-detail-view.xml")
@EditedEntityContainer("authorDc")
public class AuthorDetailView extends StandardDetailView<Author> {
}