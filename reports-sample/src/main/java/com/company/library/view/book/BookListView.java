package com.company.library.view.book;

import com.company.library.entity.Book;

import com.company.library.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.Actions;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.reportsflowui.action.RunReportAction;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "books", layout = MainView.class)
@ViewController("Book.list")
@ViewDescriptor("book-list-view.xml")
@LookupComponent("booksDataGrid")
@DialogMode(width = "50em")
public class BookListView extends StandardListView<Book> {
    @ViewComponent
    private JmixButton runBtn;
    @Autowired
    private Actions actions;

    @Subscribe
    public void onInit(final InitEvent event) {
        RunReportAction<Book> action = actions.create(RunReportAction.ID);
        runBtn.setAction(action);
    }
}