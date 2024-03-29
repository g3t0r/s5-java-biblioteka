package org.biblioteka.shared.model;

import java.time.LocalDate;

public class RentalRequestDTO {
    private String userEmail;
    private Integer copyId;
    private String until;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getCopyId() {
        return copyId;
    }

    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    static public boolean isValidDate(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }
}
