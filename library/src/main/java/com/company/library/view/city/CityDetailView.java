package com.company.library.view.city;

import com.company.library.entity.City;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "cities/:id", layout = MainView.class)
@ViewController("City.detail")
@ViewDescriptor("city-detail-view.xml")
@EditedEntityContainer("cityDc")
public class CityDetailView extends StandardDetailView<City> {
}