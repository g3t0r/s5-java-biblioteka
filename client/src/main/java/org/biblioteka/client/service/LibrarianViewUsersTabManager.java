package org.biblioteka.client.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import org.biblioteka.client.HelloApplication;
import org.biblioteka.client.config.RegisteredView;

public class LibrarianViewUsersTabManager {

    private static LibrarianViewUsersTabManager instance;

    public static LibrarianViewUsersTabManager getInstance() {
        return instance;
    }

    private LibrarianViewUsersTabManager(Tab tab) {
        this.tab = tab;
    }

    private final Tab tab;

    public static void init(Tab tab) {
        instance = new LibrarianViewUsersTabManager(tab);
    }

    private void loadView(RegisteredView view) {
        try {
            Pane p = new FXMLLoader(HelloApplication.class.getResource(
                    view.getFxmlFile()))
                    .load();

            tab.setContent(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void users() {
        loadView(RegisteredView.LIBRARIAN_VIEW_USERS_TABLE);
    }

    public void userCopies() {
        loadView(RegisteredView.LIBRARIAN_VIEW_USER_COPIES);
    }
}
