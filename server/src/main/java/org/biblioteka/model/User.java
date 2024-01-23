package org.biblioteka.model;

import org.biblioteka.shared.model.Role;
import org.biblioteka.shared.model.UserDTO;

public class User extends UserDTO {

    public UserDTO toDto() {
        UserDTO dto = new UserDTO();
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setSurname(this.getSurname());
        dto.setAddress(this.getAddress());
        dto.setPesel(this.getPesel());
        dto.setPhone(this.getPhone());
        dto.setEmail(this.getEmail());
        dto.setRole(this.getRole());
        return dto;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", surname='" + this.getSurname() + '\'' +
                ", address='" + this.getAddress() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", role=" + this.getRole() +
                '}';
    }
}
