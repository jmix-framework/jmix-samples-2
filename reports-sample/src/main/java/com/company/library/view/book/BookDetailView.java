package com.company.library.view.book;

import com.company.library.entity.Book;

import com.company.library.view.main.MainView;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;
import io.jmix.core.FileRef;
import io.jmix.core.common.util.URLEncodeUtils;
import io.jmix.flowui.Actions;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.reportsflowui.action.RunSingleEntityReportAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

@Route(value = "books/:id", layout = MainView.class)
@ViewController("Book.detail")
@ViewDescriptor("book-detail-view.xml")
@EditedEntityContainer("bookDc")
public class BookDetailView extends StandardDetailView<Book> {
    @Autowired
    private Actions actions;
    @ViewComponent
    private JmixButton reportButton;
    @Autowired
    private ApplicationContext applicationContext;
    @ViewComponent
    private IFrame displayIFrame;

    @Subscribe
    public void onReady(final ReadyEvent event) {
        Book book = getEditedEntity();
        reloadIFrame(book.getReport());
    }


    @Subscribe
    public void onInitEntity(final InitEntityEvent<Book> event) {
        RunSingleEntityReportAction<Book> action = actions.create(RunSingleEntityReportAction.ID);
        action.setReportOutputName(null);
        reportButton.setAction(action);
    }

    private static final String REST_PATH = "/rest/files?fileRef=";

    private void reloadIFrame(FileRef document) {
        if (document != null) {
            Page page = UI.getCurrent().getPage();

            page.fetchCurrentURL(url -> {
                String contextPath = ((WebApplicationContext) applicationContext).getServletContext().getContextPath();
                String documentSrc = url.getProtocol() + "://" + url.getAuthority() + contextPath +
                                     REST_PATH + URLEncodeUtils.encodeUtf8(document.toString());
                displayIFrame.setSrc(documentSrc);
            });
        }
    }
}