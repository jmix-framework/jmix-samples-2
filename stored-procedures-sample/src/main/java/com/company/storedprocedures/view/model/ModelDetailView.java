package com.company.storedprocedures.view.model;

import com.company.storedprocedures.entity.Model;

import com.company.storedprocedures.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "models/:id", layout = MainView.class)
@ViewController("Model.detail")
@ViewDescriptor("model-detail-view.xml")
@EditedEntityContainer("modelDc")
public class ModelDetailView extends StandardDetailView<Model> {
}