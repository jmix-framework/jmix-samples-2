package com.company.externaldata.view.task;

import com.company.externaldata.app.TaskService;
import com.company.externaldata.entity.Task;
import com.company.externaldata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@Route(value = "tasks", layout = MainView.class)
@ViewController("Task.list")
@ViewDescriptor("task-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "50em")
public class TaskListView extends StandardListView<Task> {

    @Autowired
    private TaskService taskService;

    @Install(to = "tasksDl", target = Target.DATA_LOADER)
    protected List<Task> tasksDlLoadDelegate(LoadContext<Task> loadContext) {
        return taskService.loadTasks();
    }

    @Install(to = "tasksDataGrid.remove", subject = "delegate")
    private void tasksDataGridRemoveDelegate(final Collection<Task> collection) {
        for (Task task : collection) {
            taskService.deleteTask(task);
        }
    }
}
