package org.biblioteka.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;
import org.biblioteka.client.HelloApplication;
import org.biblioteka.client.config.CurrentUserContext;
import org.biblioteka.client.config.RegisteredView;
import org.biblioteka.client.service.HttpService;
import org.biblioteka.client.service.SceneService;
import org.biblioteka.shared.model.AggregatedBooks;
import org.biblioteka.shared.model.RentalRequestDTO;

import java.time.LocalDate;

public class LibrarianViewController {
    private final HttpService httpService = HttpService.getInstance();

    private ObservableList<AggregatedBooks> booksList = FXCollections.observableArrayList();

    @FXML
    private TabPane tabs;

    //  book list tab

    @FXML
    private Tab bookListTab;

    @FXML
    private TextField searchInput;

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

    // rental

    @FXML
    private TextField userEmail;

    @FXML
    private TextField copyId;

    @FXML
    private DatePicker untilDatePicker;

    @FXML
    private Text rentalErrorText;

    @FXML
    private Text rentalSuccessText;

    // return copy

    @FXML
    private TextField returnCopyId;

    @FXML
    private Text returnCopyErrorText;

    @FXML
    private Text returnCopySuccessText;

    @FXML
    private Tab usersTab;

    @FXML
    private void initialize() {
        try {

            Pane p = new FXMLLoader(HelloApplication.class.getResource(RegisteredView.LIBRARIAN_VIEW_USERS_TABLE.getFxmlFile())).load();
            usersTab.setContent(p);
        } catch (Exception e) {}
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

        tabs.getSelectionModel().selectedItemProperty().addListener((o, fromTab, toTab) -> {
            if (toTab.getId().equals(bookListTab.getId())) {
                search();
            }
        });

        getAllBooks();

        LocalDate oneMonthLater = LocalDate.now().plusMonths(1);
        untilDatePicker.setValue(oneMonthLater);
    }

    @FXML
    private void logOut() {
        CurrentUserContext.setCurrentUser(null);
        SceneService.getInstance().activate(RegisteredView.SIGN_IN);
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

    private void updateBookTable(AggregatedBooks[] books) {
        booksList.clear();
        booksList.addAll(books);
        System.out.println(booksList);
    }

    @FXML
    private void rentBook() {
        rentalErrorText.setText("");
        rentalSuccessText.setText("");
        RentalRequestDTO rental = new RentalRequestDTO();
        rental.setCopyId(Integer.parseInt(copyId.getText()));
        rental.setUserEmail(userEmail.getText());
        rental.setUntil(untilDatePicker.getValue().toString());

        if (!RentalRequestDTO.isValidDate(untilDatePicker.getValue())) {
            rentalErrorText.setText("Okres wypożyczenia błedny");
            return;
        }

        httpService.post("http://localhost:2020/rental", rental, Void.class,
                (nothing) -> rentalSuccessText.setText("OK"),
                errorDto -> rentalErrorText.setText(errorDto.message)
        );

    }

    @FXML
    private void returnTheCopy() {
        returnCopySuccessText.setText("");
        returnCopyErrorText.setText("");
        httpService.post("http://localhost:2020/copy/" + returnCopyId.getText() + "/return", null, Void.class,
                (nothing) -> returnCopySuccessText.setText("OK"),
                errorDto -> returnCopyErrorText.setText(errorDto.message)
        );
    }
}
