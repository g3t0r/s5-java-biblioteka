module org.biblioteka.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.biblioteka.client to javafx.fxml;
    exports org.biblioteka.client;
}