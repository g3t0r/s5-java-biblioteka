package org.biblioteka.client.config;

import org.biblioteka.shared.model.UserDTO;

public class CurrentUserContext {

    private static UserDTO currentUser;

    public static UserDTO getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserDTO currentUser) {
        CurrentUserContext.currentUser = currentUser;
    }
}
