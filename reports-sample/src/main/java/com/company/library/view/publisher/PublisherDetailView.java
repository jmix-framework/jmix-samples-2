package com.company.library.view.publisher;

import com.company.library.entity.Publisher;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "publishers/:id", layout = MainView.class)
@ViewController("Publisher.detail")
@ViewDescriptor("publisher-detail-view.xml")
@EditedEntityContainer("publisherDc")
public class PublisherDetailView extends StandardDetailView<Publisher> {
}