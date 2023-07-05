package com.company.storedprocedures.view.model;

import com.company.storedprocedures.entity.Model;

import com.company.storedprocedures.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "models", layout = MainView.class)
@ViewController("Model.list")
@ViewDescriptor("model-list-view.xml")
@LookupComponent("modelsDataGrid")
@DialogMode(width = "64em")
public class ModelListView extends StandardListView<Model> {
}