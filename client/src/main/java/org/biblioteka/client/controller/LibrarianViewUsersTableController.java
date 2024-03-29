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
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import org.biblioteka.client.config.CurrentUserContext;
import org.biblioteka.client.service.HttpService;
import org.biblioteka.client.service.LibrarianViewUsersTabManager;
import org.biblioteka.shared.model.Role;
import org.biblioteka.shared.model.UserDTO;

import java.util.Arrays;

public class LibrarianViewUsersTableController {


    private final HttpService httpService = HttpService.getInstance();

    @FXML
    private TableView<UserDTO> table;

    @FXML
    private TableColumn<UserDTO, Integer> colID;

    @FXML
    private TableColumn<UserDTO, String> colName;
    @FXML
    private TableColumn<UserDTO, String> colSurname;
    @FXML
    private TableColumn<UserDTO, String> colAddress;
    @FXML
    private TableColumn<UserDTO, String> colEmail;
    @FXML
    private TextField searchField;

    @FXML
    private void onClick(MouseEvent event) {
        if (event.getClickCount() != 2) {
            return;
        }
        CurrentUserContext.variables.put("selectedUser",
                table.getSelectionModel().getSelectedItem());

        LibrarianViewUsersTabManager.getInstance().userCopies();
    }

    private ObservableList<UserDTO> userList = FXCollections.observableArrayList();

    @FXML
    private void onEnter(ActionEvent event) {
        fetchUsers();
    }

    @FXML
    private void initialize() {

        table.setItems(userList);

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setCellFactory(TextFieldTableCell.forTableColumn());

        colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAddress.setCellFactory(TextFieldTableCell.forTableColumn());

        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());

        fetchUsers();
    }

    @FXML
    private void fetchUsers() {
        if (searchField.getText() != null && !searchField.getText().isBlank()) {
            searchUsers(searchField.getText());
        } else {
            getAllUsers();
        }
    }

    private void searchUsers(String query) {
        String url = String.format("http://localhost:2020/users?role=%s&query=%s",
                Role.CUSTOMER, query);

        httpService.get(url, UserDTO[].class, this::updateTable,
                (errorDTO) -> System.err.println(errorDTO.message));
    }

    private void getAllUsers() {
        httpService.get("http://localhost:2020/users?role=" + Role.CUSTOMER,
                UserDTO[].class,
                this::updateTable,
                (errorDTO) -> System.err.println(errorDTO.message));
    }

    private void updateTable(UserDTO[] userDTOS) {
        System.out.println(Arrays.toString(userDTOS));
        userList.clear();
        userList.addAll(userDTOS);
    }
}
