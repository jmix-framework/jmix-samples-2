package com.company.library.view.publisher;

import com.company.library.entity.Publisher;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "publishers", layout = MainView.class)
@ViewController("Publisher.list")
@ViewDescriptor("publisher-list-view.xml")
@LookupComponent("publishersDataGrid")
@DialogMode(width = "50em")
public class PublisherListView extends StandardListView<Publisher> {
}