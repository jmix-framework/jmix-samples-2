package com.company.library.view.author;

import com.company.library.entity.Author;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.Actions;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.reportsflowui.action.RunListEntityReportAction;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "authors", layout = MainView.class)
@ViewController("Author.list")
@ViewDescriptor("author-list-view.xml")
@LookupComponent("authorsDataGrid")
@DialogMode(width = "50em")
public class AuthorListView extends StandardListView<Author> {
    @ViewComponent
    private JmixButton runListBtn;
    @Autowired
    private Actions actions;
    @Subscribe
    public void onInit(final InitEvent event) {
        RunListEntityReportAction<Author> action = actions.create(RunListEntityReportAction.ID);
        runListBtn.setAction(action);
    }
}