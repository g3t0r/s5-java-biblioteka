package org.biblioteka.client.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.biblioteka.client.HelloApplication;
import org.biblioteka.client.config.RegisteredView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneService {
    private final Scene scene;
    private Map<String, Pane> panes = new HashMap<>();

    private SceneService(Scene scene) {
        this.scene = scene;
        addPane(RegisteredView.INCORRECT);
    }

    public void addPane(RegisteredView view) {
        try {
            panes.put(view.name(), new FXMLLoader(HelloApplication.class.getResource(view.getFxmlFile())).load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void activate(RegisteredView view) {
        activate(view.name());
    }

    private void addPane(String name, Pane pane) {
        panes.put(name, pane);
    }

    private void activate(String name) {
        if(panes.containsKey(name)) {
            scene.setRoot(panes.get(name));
        } else {
            activate(RegisteredView.INCORRECT);
        }
    }


    public static void buildInstance(Scene scene) {
        if (instance != null) {
            throw new IllegalStateException("Instance already exists");
        }
        instance = new SceneService(scene);
    }

    public static SceneService getInstance() {
        return instance;
    }

    private static SceneService instance = null;
}
