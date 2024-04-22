package com.company.library.view.book;

import com.company.library.entity.Book;

import com.company.library.view.main.MainView;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorage;
import io.jmix.flowui.Actions;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.reports.runner.ReportRunner;
import io.jmix.reports.yarg.reporting.ReportOutputDocument;
import io.jmix.reportsflowui.action.RunReportAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;

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
    @ViewComponent
    private DataGrid<Book> booksDataGrid;
    @Autowired
    private FileStorage fileStorage;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private ReportRunner reportRunner;
    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onInit(final InitEvent event) {
        RunReportAction<Book> action = actions.create(RunReportAction.ID);
        runBtn.setAction(action);
    }

    @Subscribe(id = "generateBtn", subject = "clickListener")
    public void onGenerateBtnClick(final ClickEvent<JmixButton> event) {
        Book book = booksDataGrid.getSingleSelectedItem();
        if (book == null) {
            return;
        }

        final ReportOutputDocument document = reportRunner.byReportCode("book-report")
                .addParam("entity", book)
                .run();

        final byte[] reportContent = document.getContent();

        FileRef fileRef = fileStorage.saveStream(document.getDocumentName(),
                new ByteArrayInputStream(reportContent));
        book.setReport(fileRef);

        dataManager.save(book);

        notifications.create("Report generated successfully!")
                .withPosition(Notification.Position.TOP_END)
                .withType(Notifications.Type.SUCCESS)
                .show();
    }


}