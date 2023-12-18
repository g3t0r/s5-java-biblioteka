package org.biblioteka.client.service;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class SceneService {
    private final Scene scene;
    private Map<String, Pane> panes = new HashMap<>();

    private SceneService(Scene scene) {
        this.scene = scene;
    }

    public void addPane(String name, Pane pane) {
       panes.put(name, pane);
    }

    public void activate(String name) {
        scene.setRoot(panes.get(name));
    }


    public static void buildInstance(Scene scene) {
        if(instance != null) {
            throw new IllegalStateException("Instance already exists");
        }
        instance = new SceneService(scene);
    }

    public static SceneService getInstance() {
        return instance;
    }

    private static SceneService instance = null;
}
