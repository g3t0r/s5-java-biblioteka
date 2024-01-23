package org.biblioteka.client.config;

public enum RegisteredView {
    SIGN_IN("signin-view.fxml"),
    SIGN_UP("signup-view.fxml"),
    LIBRARIAN_VIEW("librarian-view.fxml"),
    LIBRARIAN_VIEW_USERS_TABLE("librarian-view-users-table.fxml"),
    LIBRARIAN_VIEW_USER_COPIES("librarian-view-user-copies.fxml"),
    ADMIN_VIEW("admin-view.fxml"),
    BOOK_TABLE("book-table-view.fxml"),
    CUSTOMER_VIEW("customer-view.fxml"),
    INCORRECT("incorrect-view.fxml");

    RegisteredView(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }
    private final String fxmlFile;

    public String getFxmlFile() {
        return fxmlFile;
    }
}
