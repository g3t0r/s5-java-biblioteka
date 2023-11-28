module org.biblioteka.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp3;
    requires kotlin.stdlib;
    requires java.biblioteka.shared.main;


    opens org.biblioteka.client to javafx.fxml;
    exports org.biblioteka.client;

    opens org.biblioteka.client.controller to javafx.fxml;
    exports org.biblioteka.client.controller;
}