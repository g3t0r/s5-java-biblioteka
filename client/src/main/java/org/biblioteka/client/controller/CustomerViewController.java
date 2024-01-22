package org.biblioteka.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.biblioteka.client.config.CurrentUserContext;
import org.biblioteka.client.config.RegisteredView;
import org.biblioteka.client.service.HttpService;
import org.biblioteka.client.service.SceneService;
import org.biblioteka.shared.model.AggregatedBooks;
import org.biblioteka.shared.model.RentedCopy;

import java.util.Arrays;

public class CustomerViewController {

    private final HttpService httpService = HttpService.getInstance();

    private ObservableList<RentedCopy> rents = FXCollections.observableArrayList();

    private ObservableList<AggregatedBooks> booksList = FXCollections.observableArrayList();

    @FXML
    private TableView<RentedCopy> rentsTab;

    @FXML
    private TableView<AggregatedBooks> booksListTab;

    @FXML
    private TableColumn<RentedCopy, Integer> rentsTab_copyIdColumn;

    @FXML
    private TableColumn<RentedCopy, String> rentsTab_titleColumn;

    @FXML
    private TableColumn<RentedCopy, String> rentsTab_authorColumn;

    @FXML
    private TableColumn<RentedCopy, String> rentsTab_fromColumn;

    @FXML
    private TableColumn<RentedCopy, String> rentsTab_untilColumn;

    @FXML
    private TableColumn booksListTab_titleColumn;
    @FXML
    private TableColumn booksListTab_authorColumn;

    @FXML
    private TableColumn booksListTab_genreColumn;

    @FXML
    private TableColumn booksListTab_availableColumn;

    @FXML
    private TextField searchInput;

    @FXML
    private void logOut() {
        CurrentUserContext.setCurrentUser(null);
        SceneService.getInstance().activate(RegisteredView.SIGN_IN);
    }

    private void updateBookTable(AggregatedBooks[] books) {
        booksList.clear();
        booksList.addAll(books);
        System.out.println(booksList);
    }

    private void getAllBooks() {
        httpService.get("http://localhost:2020/books", AggregatedBooks[].class,
                this::updateBookTable,
                errorDto -> {
                    System.err.println(errorDto.message);
                });
    }

    private void queryBooks(String text) {
        httpService.get("http://localhost:2020/books?search=" + text, AggregatedBooks[].class,
                this::updateBookTable,
                errorDto -> {
                    System.err.println(errorDto.message);
                });
    }

    @FXML
    private void onEnter(ActionEvent event) {
        search();
    }

    @FXML
    private void search() {
        if (searchInput.getText() != null && !searchInput.getText().isBlank()) {
            queryBooks(searchInput.getText());
        } else {
            getAllBooks();
        }
    }

    @FXML
    private void initialize() {
        rentsTab.setItems(rents);

        rentsTab_copyIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        rentsTab_titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rentsTab_authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rentsTab_fromColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rentsTab_untilColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        rentsTab_copyIdColumn.setCellValueFactory(new PropertyValueFactory<>("copyId"));
        rentsTab_titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        rentsTab_authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        rentsTab_fromColumn.setCellValueFactory(new PropertyValueFactory<>("rentedAt"));
        rentsTab_untilColumn.setCellValueFactory(new PropertyValueFactory<>("rentedUntil"));

        httpService.get("http://localhost:2020/copy?userEmail=" +
                        CurrentUserContext.getCurrentUser().getEmail(),
                RentedCopy[].class,
                copyArray -> {
                    rents.clear();
                    rents.addAll(Arrays.asList(copyArray));
                },
                errorDto -> System.err.println(errorDto));

        booksListTab.setItems(booksList);
        booksListTab_titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        booksListTab_titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        booksListTab_authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        booksListTab_authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        booksListTab_genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        booksListTab_genreColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        booksListTab_availableColumn.setCellValueFactory(new PropertyValueFactory<AggregatedBooks, Integer>("available"));
        booksListTab_availableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        getAllBooks();
    }
}
