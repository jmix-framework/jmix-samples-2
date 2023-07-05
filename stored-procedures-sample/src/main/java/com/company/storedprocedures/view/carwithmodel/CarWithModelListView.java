package com.company.storedprocedures.view.carwithmodel;

import com.company.storedprocedures.app.CarWithModelService;
import com.company.storedprocedures.entity.CarWithModel;
import com.company.storedprocedures.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.textfield.JmixIntegerField;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Route(value = "carWithModels", layout = MainView.class)
@ViewController("CarWithModel.list")
@ViewDescriptor("car-with-model-list-view.xml")
@LookupComponent("carWithModelsDataGrid")
@DialogMode(width = "50em")
public class CarWithModelListView extends StandardListView<CarWithModel> {

    @ViewComponent
    private JmixIntegerField yearFilterField;
    @Autowired
    private Notifications notifications;
    @ViewComponent
    private CollectionLoader<CarWithModel> carWithModelsDl;
    @Autowired
    private CarWithModelService carWithModelService;

    @Subscribe
    public void onInit(final InitEvent event) {
        yearFilterField.setValue(2021);
    }

    @Subscribe("yearFilterField")
    public void onYearFilterFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixIntegerField, Integer> event) {
        if (event.getValue() == null || event.getValue() == 0) {
            notifications.create("Enter a year").show();
        } else {
            carWithModelsDl.load();
        }
    }

    @Install(to = "carWithModelsDl", target = Target.DATA_LOADER)
    private List<CarWithModel> carWithModelsDlLoadDelegate(LoadContext<CarWithModel> loadContext) {
        Integer year = yearFilterField.getValue();
        if (year == null) {
            return Collections.emptyList();
        } else {
            return carWithModelService.loadCarWithModelByYear(year);
        }
    }
}
