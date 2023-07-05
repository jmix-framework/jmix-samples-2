package com.company.library.view.literaturetype;

import com.company.library.entity.LiteratureType;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "literatureTypes/:id", layout = MainView.class)
@ViewController("LiteratureType.detail")
@ViewDescriptor("literature-type-detail-view.xml")
@EditedEntityContainer("literatureTypeDc")
public class LiteratureTypeDetailView extends StandardDetailView<LiteratureType> {
}