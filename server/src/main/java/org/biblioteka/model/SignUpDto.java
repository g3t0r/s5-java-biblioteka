package org.biblioteka.model;

public class SignUpDto {
    public String name;
    public String surname;
    public String address;
    public String email;
    public String password;
    public String role;


    @Override
    public String toString() {
        return "SignUpDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
