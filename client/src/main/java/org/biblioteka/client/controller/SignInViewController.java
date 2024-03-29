package org.biblioteka.client.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.biblioteka.client.config.CurrentUserContext;
import org.biblioteka.client.config.RegisteredView;
import org.biblioteka.client.service.HttpService;
import org.biblioteka.client.service.SceneService;
import org.biblioteka.shared.model.LogInDto;
import org.biblioteka.shared.model.Role;
import org.biblioteka.shared.model.UserDTO;

public class SignInViewController {

    private final HttpService httpService = HttpService.getInstance();

    @FXML
    private void onEnter(ActionEvent event) {
        submitForm();
    }


    @FXML
    private Label errorLabel;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private void navigateToSignUp() {
        SceneService.getInstance().activate(RegisteredView.SIGN_UP);
    }

    private void clearForm() {
        email.setText("");
        password.setText("");
        errorLabel.setText("");
    }

    @FXML
    private void submitForm() {
        LogInDto dto = new LogInDto();
        dto.setEmail(email.getText());
        dto.setPassword(password.getText());

        httpService.post("http://localhost:2020/login", dto, UserDTO.class,
                (userDto) -> {
                    CurrentUserContext.setCurrentUser(userDto);

                    if(userDto.getRole().toString().equals(Role.CUSTOMER.toString())) {
                        SceneService.getInstance().addPane(RegisteredView.CUSTOMER_VIEW);
                        SceneService.getInstance().activate(RegisteredView.CUSTOMER_VIEW);
                    } else if(userDto.getRole().toString().equals(Role.EMPLOYEE.toString())) {
                        SceneService.getInstance().addPane(RegisteredView.LIBRARIAN_VIEW);
                        SceneService.getInstance().activate(RegisteredView.LIBRARIAN_VIEW);
                    } else {
                        SceneService.getInstance().addPane(RegisteredView.ADMIN_VIEW);
                        SceneService.getInstance().activate(RegisteredView.ADMIN_VIEW);
                    }

                    clearForm();
                },
                (errorDto) -> Platform.runLater(() -> errorLabel.setText(errorDto.message)));
    }

}
