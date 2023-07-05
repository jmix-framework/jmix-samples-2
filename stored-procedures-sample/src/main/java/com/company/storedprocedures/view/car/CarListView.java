package com.company.storedprocedures.view.car;

import com.company.storedprocedures.entity.Car;

import com.company.storedprocedures.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "cars", layout = MainView.class)
@ViewController("Car.list")
@ViewDescriptor("car-list-view.xml")
@LookupComponent("carsDataGrid")
@DialogMode(width = "64em")
public class CarListView extends StandardListView<Car> {
}