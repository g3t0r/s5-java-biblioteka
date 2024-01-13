package org.biblioteka.auth;

import org.biblioteka.shared.model.Role;

public class UserAuthInfo {
    private final Integer id;
    private final Role role;

    public UserAuthInfo(Integer id, Role role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserAuthInfo{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
