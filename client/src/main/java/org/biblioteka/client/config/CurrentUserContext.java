package org.biblioteka.client.config;

import org.biblioteka.shared.model.UserDTO;

import java.util.HashMap;
import java.util.Map;

public class CurrentUserContext {

    public static Map<String, Object> variables = new HashMap<>();

    private static UserDTO currentUser;

    public static UserDTO getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserDTO currentUser) {
        CurrentUserContext.currentUser = currentUser;
    }
}
