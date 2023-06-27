package com.company.library.app;

import com.company.library.entity.*;
import io.jmix.core.DataManager;
import io.jmix.core.Resources;
import io.jmix.core.security.Authenticated;
import io.jmix.reports.ReportImportExport;
import jakarta.persistence.LockModeType;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class DemoDataInitializer {
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Resources resources;
    @Autowired
    protected ReportImportExport reportImportExport;
    private static final Logger log = LoggerFactory.getLogger(DemoDataInitializer.class);

    private final static String REPORT_LOCATION = "com/company/library/reports/";

    @EventListener
    @Authenticated
    public void onApplicationStarted(ApplicationStartedEvent event) {
        if (dataManager.load(City.class).all().maxResults(1).list().size() > 0) {
            return;
        }
        List<City> cities = initCities();
        List<Publisher> publishers = initPublishers();
        List<LiteratureType> types = initLiteratureTypes();
        List<LibraryDepartment> departments = initLibraryDepartments();
        List<Author> authors = initAuthors();
        List<Book> books = initBooks(types);
        List<BookPublication> publications = initBookPublications(books,publishers,cities);
        List<BookInstance> bookInstances = initBookInstances(publications,departments);
        importReport();
    }

    private List<City> initCities() {
        City city;
        ArrayList<City> list = new ArrayList<>();

        city = dataManager.create(City.class);
        city.setName("Riverside");
        list.add(dataManager.save(city));

        city = dataManager.create(City.class);
        city.setName("New York");
        list.add(dataManager.save(city));

        city = dataManager.create(City.class);
        city.setName("Los Angeles");
        list.add(dataManager.save(city));

        return list;
    }

    private List<Publisher> initPublishers() {
        Publisher publisher;
        ArrayList<Publisher> list = new ArrayList<>();

        publisher = dataManager.create(Publisher.class);
        publisher.setName("Corner Street Publisher");
        list.add(dataManager.save(publisher));

        publisher = dataManager.create(Publisher.class);
        publisher.setName("Phaidon Press");
        list.add(dataManager.save(publisher));

        return list;
    }

    private List<LiteratureType> initLiteratureTypes() {
        LiteratureType literatureType;
        ArrayList<LiteratureType> list = new ArrayList<>();

        literatureType = dataManager.create(LiteratureType.class);
        literatureType.setName("Economics");
        list.add(dataManager.save(literatureType));

        literatureType = dataManager.create(LiteratureType.class);
        literatureType.setName("Art");
        list.add(dataManager.save(literatureType));

        literatureType = dataManager.create(LiteratureType.class);
        literatureType.setName("Management Skills");
        list.add(dataManager.save(literatureType));

        return list;
    }

    private List<LibraryDepartment> initLibraryDepartments() {
        LibraryDepartment LibraryDepartment;
        ArrayList<LibraryDepartment> list = new ArrayList<>();

        LibraryDepartment = dataManager.create(LibraryDepartment.class);
        LibraryDepartment.setName("Social Science");
        list.add(dataManager.save(LibraryDepartment));

        LibraryDepartment = dataManager.create(LibraryDepartment.class);
        LibraryDepartment.setName("Economics");
        list.add(dataManager.save(LibraryDepartment));

        LibraryDepartment = dataManager.create(LibraryDepartment.class);
        LibraryDepartment.setName("Language and Literature");
        list.add(dataManager.save(LibraryDepartment));

        LibraryDepartment = dataManager.create(LibraryDepartment.class);
        LibraryDepartment.setName("Liberal Arts");
        list.add(dataManager.save(LibraryDepartment));

        LibraryDepartment = dataManager.create(LibraryDepartment.class);
        LibraryDepartment.setName("Natural Science");
        list.add(dataManager.save(LibraryDepartment));

        return list;
    }

    private List<Author> initAuthors() {
        Author author;
        ArrayList<Author> list = new ArrayList<>();

        author = dataManager.create(Author.class);
        author.setFirstName("Peter");
        author.setLastName("Block");
        list.add(dataManager.save(author));

        author = dataManager.create(Author.class);
        author.setFirstName("Curtis");
        author.setLastName("Ray");
        list.add(dataManager.save(author));

        author = dataManager.create(Author.class);
        author.setFirstName("Ursula");
        author.setLastName("Runde");
        list.add(dataManager.save(author));

        return list;
    }

    private List<Book> initBooks(List<LiteratureType> types) {
        Book book;
        ArrayList<Book> list = new ArrayList<>();

        book = dataManager.create(Book.class);
        book.setName("The Lost Science of Compound Interest");
        book.setLiteratureType(types.get(0));
        book.setSummary("In a new and compelling story about money, science, art, evolution, discovery, creation, struggle, " +
                "and ultimately, triumph, Curtis will take you on a life-changing journey through some of the most complicated " +
                "mathematic money concepts by transforming them into an easily implemented path to unlimited wealth and prosperity");
        list.add(dataManager.save(book));

        book = dataManager.create(Book.class);
        book.setName("The 20th Century Art Book");
        book.setLiteratureType(types.get(1));
        book.setSummary("The 20th Century Art Book was hailed upon its release as an exciting celebration of the myriad forms " +
                "assumed by art over the last century");
        list.add(dataManager.save(book));

        book = dataManager.create(Book.class);
        book.setName("Stewardship: Choosing Service Over Self-Interest");
        book.setLiteratureType(types.get(2));
        book.setSummary("Stewardship was provocative, even revolutionary, when it was first published in 1993, and it remains " +
                "every bit as relevant and radical today");
        list.add(dataManager.save(book));

        return list;
    }

    private List<BookPublication> initBookPublications(List<Book> books, List<Publisher> publishers, List<City> cities) {
        BookPublication bookPublication;
        ArrayList<BookPublication> list = new ArrayList<>();

        bookPublication = dataManager.create(BookPublication.class);
        bookPublication.setYear(1999);
        bookPublication.setBook(books.get(1));
        bookPublication.setPublisher(publishers.get(1));
        bookPublication.setCity(cities.get(1));

        list.add(dataManager.save(bookPublication));

        bookPublication = dataManager.create(BookPublication.class);
        bookPublication.setYear(2013);
        bookPublication.setBook(books.get(2));
        bookPublication.setPublisher(publishers.get(0));
        bookPublication.setCity(cities.get(1));

        list.add(dataManager.save(bookPublication));

        bookPublication = dataManager.create(BookPublication.class);
        bookPublication.setYear(2015);
        bookPublication.setBook(books.get(2));
        bookPublication.setPublisher(publishers.get(1));
        bookPublication.setCity(cities.get(0));

        list.add(dataManager.save(bookPublication));

        bookPublication = dataManager.create(BookPublication.class);
        bookPublication.setYear(2020);
        bookPublication.setBook(books.get(0));
        bookPublication.setPublisher(publishers.get(1));
        bookPublication.setCity(cities.get(2));

        list.add(dataManager.save(bookPublication));

        return list;
    }

    private List<BookInstance> initBookInstances(List<BookPublication> bookPublications, List<LibraryDepartment> departments) {
        BookInstance bookInstance;
        ArrayList<BookInstance> list = new ArrayList<>();

        bookInstance = dataManager.create(BookInstance.class);
        bookInstance.setIsReference(true);
        bookInstance.setInventoryNumber(Long.getLong("12584572132"));
        bookInstance.setBookCount(20);
        bookInstance.setBookPublication(bookPublications.get(3));
        bookInstance.setLibraryDepartment(departments.get(1));

        list.add(dataManager.save(bookInstance));

        bookInstance = dataManager.create(BookInstance.class);
        bookInstance.setIsReference(false);
        bookInstance.setInventoryNumber(Long.getLong("234526243562"));
        bookInstance.setBookCount(100);
        bookInstance.setBookPublication(bookPublications.get(2));
        bookInstance.setLibraryDepartment(departments.get(0));

        list.add(dataManager.save(bookInstance));

        bookInstance = dataManager.create(BookInstance.class);
        bookInstance.setIsReference(false);
        bookInstance.setInventoryNumber(Long.getLong("32541435134"));
        bookInstance.setBookCount(85);
        bookInstance.setBookPublication(bookPublications.get(1));
        bookInstance.setLibraryDepartment(departments.get(0));

        list.add(dataManager.save(bookInstance));

        return list;
    }

    private void importReport(){
        InitFlags initFlags = dataManager.load(InitFlags.class)
                .id(1)
                .lockMode(LockModeType.PESSIMISTIC_WRITE)
                .optional()
                .orElseGet(() -> {
                    InitFlags entity = dataManager.create(InitFlags.class);
                    entity.setId(1);
                    return entity;
                });

        if (!Boolean.TRUE.equals(initFlags.getReportsInitialized())) {
            importReport("Book Items location.zip");
            importReport("Book Record.zip");
            importReport("Publication details.zip");
            importReport("Publications grouped by types and books.zip");
            importReport("Recently added book items.zip");

            initFlags.setReportsInitialized(true);
            dataManager.save(initFlags);
        }
    }
    private void importReport(String reportFileName) {
        String location = REPORT_LOCATION + reportFileName;
        log.info("Initializing report from " + location);
        try (InputStream stream = resources.getResourceAsStream(location)) {
            if (stream != null) {
                reportImportExport.importReports(IOUtils.toByteArray(stream));
            } else {
                log.info("Not found: " + location);
            }
        } catch (IOException e) {
            log.error("Unable to initialize reports", e);
        }
    }
}