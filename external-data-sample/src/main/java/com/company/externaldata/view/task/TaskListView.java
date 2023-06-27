package com.company.externaldata.view.task;

import com.company.externaldata.app.TaskService;
import com.company.externaldata.entity.Task;
import com.company.externaldata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Route(value = "tasks", layout = MainView.class)
@ViewController("Task.list")
@ViewDescriptor("task-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "50em")
public class TaskListView extends StandardListView<Task> {

    @Autowired
    private TaskService taskService;

    @ViewComponent
    private DataGrid<Task> tasksDataGrid;

    @ViewComponent
    private CollectionContainer<Task> tasksDc;

    @Install(to = "tasksDl", target = Target.DATA_LOADER)
    protected List<Task> tasksDlLoadDelegate(LoadContext<Task> loadContext) {
        return taskService.loadTasks();
    }

    @Subscribe("tasksDataGrid.remove")
    public void onTasksDataGridRemove(final ActionPerformedEvent event) {
        Task selectedTask = tasksDataGrid.getSingleSelectedItem();
        if (selectedTask != null) {
            taskService.deleteTask(selectedTask);
            tasksDc.getMutableItems().remove(selectedTask);
        }
    }
}
