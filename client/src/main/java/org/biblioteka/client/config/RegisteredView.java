package org.biblioteka.client.config;

public enum RegisteredView {
    SIGN_IN("signin-view.fxml"),
    SIGN_UP("signup-view.fxml"),
    BOOK_TABLE("book-table-view.fxml"),
    INCORRECT("incorrect-view.fxml");

    RegisteredView(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }
    private final String fxmlFile;

    public String getFxmlFile() {
        return fxmlFile;
    }
}
