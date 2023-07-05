package com.company.storedprocedures.view.car;

import com.company.storedprocedures.app.CarService;
import com.company.storedprocedures.entity.Car;

import com.company.storedprocedures.view.main.MainView;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.textfield.JmixIntegerField;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Route(value = "carsbyyear", layout = MainView.class)
@ViewController("CarByYear.list")
@ViewDescriptor("cars-by-year-list-view.xml")
@LookupComponent("carsDataGrid")
@DialogMode(width = "64em")
public class CarsByYearListView extends StandardListView<Car> {

    @ViewComponent
    private JmixIntegerField yearFilterField;

    @Autowired
    private CarService carService;

    @ViewComponent
    private CollectionLoader<Car> carsDl;

    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onInit(final InitEvent event) {
        yearFilterField.setValue(2021);
    }

    @Subscribe("yearFilterField")
    public void onYearFilterFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixIntegerField, Integer> event) {
        if (event.getValue() == null || event.getValue() == 0) {
            notifications.create("Enter a year").show();
        } else {
            carsDl.load();
        }
    }

    @Install(to = "carsDl", target = Target.DATA_LOADER)
    private List<Car> carsDlLoadDelegate(final LoadContext<Car> loadContext) {
        Integer year = yearFilterField.getValue();
        if (year == null) {
            return Collections.emptyList();
        } else {
            return carService.loadCarsByYear(year);
        }
    }
}