package com.company.storedprocedures.view.car;

import com.company.storedprocedures.entity.Car;

import com.company.storedprocedures.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "cars/:id", layout = MainView.class)
@ViewController("Car.detail")
@ViewDescriptor("car-detail-view.xml")
@EditedEntityContainer("carDc")
public class CarDetailView extends StandardDetailView<Car> {
}