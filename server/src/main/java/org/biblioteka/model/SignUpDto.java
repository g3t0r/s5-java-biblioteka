package org.biblioteka.model;

import org.biblioteka.shared.model.SignUpDTO;

public class SignUpDto extends SignUpDTO {
    @Override
    public String toString() {
        return "SignUpDto{" +
                "name='" + this.getName() + '\'' +
                ", surname='" + this.getSurname() + '\'' +
                ", address='" + this.getAddress() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", role='" + this.getRole() + '\'' +
                '}';
    }
}
