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
import org.biblioteka.shared.model.Role;
import org.biblioteka.shared.model.UserDTO;

import java.util.Arrays;

public class AdminViewController {

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
    private TableColumn<UserDTO, String> colRole;
    @FXML
    private TextField searchField;

    private ObservableList<UserDTO> userList = FXCollections.observableArrayList();

    @FXML
    private void onEnter(ActionEvent event) {
        fetchUsers();
    }

    @FXML
    private void logOut() {
        CurrentUserContext.setCurrentUser(null);
        SceneService.getInstance().activate(RegisteredView.SIGN_IN);
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

        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colRole.setCellFactory(TextFieldTableCell.forTableColumn());

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
        httpService.get("http://localhost:2020/users?query=" + query,
                UserDTO[].class,
                this::updateTable,
                (errorDTO) -> System.err.println(errorDTO.message));
    }

    private void getAllUsers() {
        httpService.get("http://localhost:2020/users",
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
