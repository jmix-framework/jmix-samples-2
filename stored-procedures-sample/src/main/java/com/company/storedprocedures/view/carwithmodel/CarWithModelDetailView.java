package com.company.storedprocedures.view.carwithmodel;

import com.company.storedprocedures.app.CarWithModelService;
import com.company.storedprocedures.entity.CarWithModel;
import com.company.storedprocedures.view.main.MainView;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.SaveContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Set;

@Route(value = "carWithModel/detail", layout = MainView.class)
@ViewController("CarWithModel.detail")
@ViewDescriptor("car-with-model-detail-view.xml")
@EditedEntityContainer("carWithModelDc")
public class CarWithModelDetailView extends StandardDetailView<CarWithModel> {

    @Autowired
    private CarWithModelService carWithModelService;

    @Override
    protected void findEntityId(BeforeEnterEvent event) {
        // Because DTO entity cannot be loaded by Id, we need to prevent Id parsing from route parameters
    }

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> saveDelegate(final SaveContext saveContext) {
        CarWithModel editedEntity = getEditedEntity();
        carWithModelService.save(editedEntity);
        return Collections.singleton(editedEntity);
    }
}
