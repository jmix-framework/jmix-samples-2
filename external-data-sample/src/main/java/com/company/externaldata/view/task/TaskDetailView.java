package com.company.externaldata.view.task;

import com.company.externaldata.app.TaskService;
import com.company.externaldata.entity.Task;
import com.company.externaldata.view.main.MainView;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.SaveContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Route(value = "task/detail", layout = MainView.class)
@ViewController("Task.detail")
@ViewDescriptor("task-detail-view.xml")
@EditedEntityContainer("taskDc")
public class TaskDetailView extends StandardDetailView<Task> {

    @Autowired
    private TaskService taskService;

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> saveDelegate(final SaveContext saveContext) {
        Task task = getEditedEntity();
        Task savedTask = taskService.saveTask(task);
        return Set.of(savedTask);
    }

    @Override
    protected void findEntityId(BeforeEnterEvent event) {
        // Because DTO entity cannot be loaded by Id, we need to prevent Id parsing from route parameters
    }
}
