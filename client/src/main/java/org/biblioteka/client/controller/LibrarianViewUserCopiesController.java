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
import org.biblioteka.client.service.LibrarianViewUsersTabManager;
import org.biblioteka.shared.model.RentedCopy;
import org.biblioteka.shared.model.UserDTO;

import java.util.Arrays;

public class LibrarianViewUserCopiesController {


    private final HttpService httpService = HttpService.getInstance();

    private ObservableList<RentedCopy> rents = FXCollections.observableArrayList();

    @FXML
    private TableView<RentedCopy> rentsTab;

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

        UserDTO selectedUser = (UserDTO) CurrentUserContext.variables.get("selectedUser");

        httpService.get("http://localhost:2020/copy?userEmail=" +
                        selectedUser.getEmail(),
                RentedCopy[].class,
                copyArray -> {
                    rents.clear();
                    rents.addAll(Arrays.asList(copyArray));
                },
                errorDto -> System.err.println(errorDto));
    }

    @FXML
    private void goBack() {
        LibrarianViewUsersTabManager.getInstance().users();
    }
}
