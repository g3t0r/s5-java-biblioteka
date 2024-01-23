package org.biblioteka.client.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.biblioteka.client.config.CurrentUserContext;
import org.biblioteka.client.service.HttpService;
import org.biblioteka.client.service.LibrarianViewUsersTabManager;
import org.biblioteka.shared.model.RentalRequestDTO;
import org.biblioteka.shared.model.UserDTO;

import java.time.LocalDate;

public class RentCopyViewController {

    private final HttpService httpService = HttpService.getInstance();

    @FXML
    public TextField userEmail;

    @FXML
    private TextField copyId;

    @FXML
    private DatePicker untilDatePicker;

    @FXML
    private Text rentalErrorText;

    @FXML
    private Text rentalSuccessText;

    @FXML
    private void initialize()  {
        UserDTO user = (UserDTO) CurrentUserContext.variables.get("selectedUser");
        userEmail.setText(user.getEmail());
        LocalDate oneMonthLater = LocalDate.now().plusMonths(1);
        untilDatePicker.setValue(oneMonthLater);
    }

    @FXML
    private void rentBook() {
        rentalErrorText.setText("");
        rentalSuccessText.setText("");
        RentalRequestDTO rental = new RentalRequestDTO();
        int intCopyId;
        try {
            intCopyId = Integer.parseInt(copyId.getText());
        } catch (NumberFormatException nfe) {
            rentalErrorText.setText("Id egzemplarza musi być liczbą");
            return;
        }

        rental.setCopyId(intCopyId);
        rental.setUserEmail(userEmail.getText());
        rental.setUntil(untilDatePicker.getValue().toString());

        if (!RentalRequestDTO.isValidDate(untilDatePicker.getValue())) {
            rentalErrorText.setText("Okres wypożyczenia błedny");
            return;
        }

        httpService.post("http://localhost:2020/rental", rental, Void.class,
                (nothing) -> Platform.runLater(this::goBack),
                errorDto -> rentalErrorText.setText(errorDto.message)
        );
    }

    @FXML
    private void goBack() {
        LibrarianViewUsersTabManager.getInstance().userCopies();
    }
}
