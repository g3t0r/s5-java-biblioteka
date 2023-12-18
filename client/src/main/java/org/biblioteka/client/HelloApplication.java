package org.biblioteka.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.biblioteka.client.config.RegisteredView;
import org.biblioteka.client.service.SceneService;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(new Pane(), 1200, 800);
        SceneService.buildInstance(scene);
        SceneService sceneService = SceneService.getInstance();
        sceneService.addPane(RegisteredView.SIGN_UP);
        sceneService.addPane(RegisteredView.SIGN_IN);
        sceneService.activate(RegisteredView.SIGN_IN);

        stage.setTitle("Biblioteka");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}