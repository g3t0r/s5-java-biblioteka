package org.biblioteka.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.biblioteka.client.config.CurrentUserContext;
import org.biblioteka.client.service.HttpService;
import org.biblioteka.shared.model.RentedCopy;

import java.util.Arrays;

public class CustomerViewController {

    private final HttpService httpService = HttpService.getInstance();

    private ObservableList<RentedCopy> items = FXCollections.observableArrayList();

    @FXML
    private TableView<RentedCopy> tableView;

    @FXML
    private TableColumn<RentedCopy, Integer> copyIdColumn;

    @FXML
    private TableColumn<RentedCopy, String> titleColumn;

    @FXML
    private TableColumn<RentedCopy, String> authorColumn;

    @FXML
    private TableColumn<RentedCopy, String> fromColumn;

    @FXML
    private TableColumn<RentedCopy, String> untilColumn;


    @FXML
    private void initialize() {

        copyIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fromColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        untilColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        copyIdColumn.setCellValueFactory(new PropertyValueFactory<>("copyId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("rentedAt"));
        untilColumn.setCellValueFactory(new PropertyValueFactory<>("rentedUntil"));

        tableView.setItems(items);
        httpService.get("http://localhost:2020/copy?userEmail=" +
                        CurrentUserContext.getCurrentUser().getEmail(),
                RentedCopy[].class,
                copyArray -> {
                    items.clear();
                    items.addAll(Arrays.asList(copyArray));
                },
                errorDto -> System.err.println(errorDto));
    }
}
