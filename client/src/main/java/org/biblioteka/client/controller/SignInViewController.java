package org.biblioteka.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.biblioteka.client.service.HttpService;
import org.biblioteka.shared.model.LogInDto;
import org.biblioteka.shared.model.UserDTO;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SignInViewController {

    private final HttpService httpService = HttpService.getInstance();


    @FXML
    private ToggleGroup group;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private void submitForm() throws Exception{
        LogInDto dto = new LogInDto();
        dto.setEmail(email.getText());
        dto.setPassword(password.getText());
        Future<UserDTO> response =  httpService
                .post("http://localhost:2020/login", dto, UserDTO.class);
        UserDTO user = response.get(10, TimeUnit.SECONDS);
        if(user == null) {
            System.out.println("Login failed");
        } else {
            System.out.println(user);
        }
    }

}
