package org.biblioteka.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.biblioteka.client.service.SceneService;

public class SignUpController {
    @FXML
    private Label errorLabel;

    @FXML
    private void navigateToSignIn() {
        SceneService.getInstance().activate("sign-in");
    }
}
