package org.biblioteka.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.biblioteka.client.service.SceneService;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(new Pane(), 1200, 800);
        SceneService.buildInstance(scene);
        configureViews();
        SceneService.getInstance().activate("sign-in");

        stage.setTitle("Biblioteka");
        stage.setScene(scene);
        stage.show();
    }

    void configureViews() throws IOException {
        SceneService sceneService = SceneService.getInstance();
        sceneService.addPane("sign-up", new FXMLLoader(getClass().getResource("signup-view.fxml")).load());
        sceneService.addPane("sign-in", new FXMLLoader(getClass().getResource("signin-view.fxml")).load());
    }

    public static void main(String[] args) {
        launch();
    }
}