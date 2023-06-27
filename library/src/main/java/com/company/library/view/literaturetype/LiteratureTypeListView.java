package com.company.library.view.literaturetype;

import com.company.library.entity.LiteratureType;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "literatureTypes", layout = MainView.class)
@ViewController("LiteratureType.list")
@ViewDescriptor("literature-type-list-view.xml")
@LookupComponent("literatureTypesDataGrid")
@DialogMode(width = "50em")
public class LiteratureTypeListView extends StandardListView<LiteratureType> {
}