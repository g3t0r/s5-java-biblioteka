package org.biblioteka.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.biblioteka.client.service.HttpService;
import org.biblioteka.shared.model.AggregatedBooks;

public class BookTableViewController {

    private final HttpService httpService = HttpService.getInstance();

    private ObservableList<AggregatedBooks> booksList = FXCollections.observableArrayList();

    @FXML
    private TableView<AggregatedBooks> tableView;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn authorColumn;

    @FXML
    private TableColumn genreColumn;
    @FXML
    private TableColumn availableColumn;
    @FXML
    private TableColumn totalColumn;

    @FXML
    private void initialize() {
        tableView.setItems(booksList);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        availableColumn.setCellValueFactory(new PropertyValueFactory<AggregatedBooks, Integer>("available"));
        availableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        totalColumn.setCellValueFactory(new PropertyValueFactory<AggregatedBooks, Integer>("total"));
        totalColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));



        httpService.get("http://localhost:2020/books", AggregatedBooks[].class,
                books -> {
                    booksList.clear();
                    booksList.addAll(books);
                    System.out.println(booksList);
                },
                errorDto -> {
                    System.err.println(errorDto.message);
                });
    }

}
