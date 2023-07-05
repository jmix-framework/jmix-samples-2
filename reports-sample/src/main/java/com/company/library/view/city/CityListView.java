package com.company.library.view.city;

import com.company.library.entity.City;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "cities", layout = MainView.class)
@ViewController("City.list")
@ViewDescriptor("city-list-view.xml")
@LookupComponent("citiesDataGrid")
@DialogMode(width = "50em")
public class CityListView extends StandardListView<City> {
}