package org.biblioteka.client.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.biblioteka.client.config.CurrentUserContext;
import org.biblioteka.client.config.RegisteredView;
import org.biblioteka.client.service.HttpService;
import org.biblioteka.client.service.SceneService;
import org.biblioteka.shared.model.Role;
import org.biblioteka.shared.model.SignUpDTO;
import org.biblioteka.shared.model.UserDTO;

public class SignUpController {

    private final HttpService httpService = HttpService.getInstance();

    @FXML
    private void onEnter(ActionEvent event) {
        submitForm();
    }

    @FXML
    private Label errorLabel;

    @FXML
    private RadioButton clientRadioButton;
    @FXML
    private RadioButton workerRadioButton;
    @FXML
    private RadioButton adminRadioButton;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repeatPassword;

    public String getRole() {
        if (clientRadioButton.isSelected()) {
            return Role.CUSTOMER.toString();
        } else if (workerRadioButton.isSelected()) {
            return Role.EMPLOYEE.toString();
        } else if (adminRadioButton.isSelected()) {
            return Role.ADMIN.toString();
        }
        return null;
    }

    @FXML
    private void navigateToSignIn() {
        SceneService.getInstance().activate(RegisteredView.SIGN_IN);
    }

    private boolean validateForm() {
        if(!name.getText().matches("^[a-zA-Z]+$")) {
            errorLabel.setText("Imię może zawierać tylko litery");
            return false;
        }
        if(!surname.getText().matches("^[a-zA-Z]+$")) {
            errorLabel.setText("Nazwisko może zawierać tylko litery");
            return false;
        }

        if(!email.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            errorLabel.setText("Niepoprawny adres email");
            return false;
        }

        if(password.getText().length() < 8) {
            errorLabel.setText("Hasło musi mieć co najmniej 8 znaków");
            return false;
        }

        if(!password.getText().equals(repeatPassword.getText())) {
            errorLabel.setText("Hasła muszą pasować ze sobą");
            return false;
        }

        return true;
    }

    @FXML
    private void submitForm() {
        if(!validateForm()) {
            return;
        }

        SignUpDTO dto = new SignUpDTO();
        dto.setName(name.getText());
        dto.setSurname(surname.getText());
        dto.setEmail(email.getText());
        dto.setPassword(password.getText());
        dto.setRole(getRole());

        httpService.post("http://localhost:2020/signup", dto, UserDTO.class,
                (userDto) -> {
                    SceneService.getInstance().addPane(RegisteredView.SIGN_IN);
                    SceneService.getInstance().activate(RegisteredView.SIGN_IN);
                },
                (errorDto) -> Platform.runLater(() -> errorLabel.setText(errorDto.message)));
    }

}
