package org.biblioteka.shared.model;

public class SimpleRental {
    private Integer copyId;
    private Integer userId;
    private String userEmail;
    private String until;
    private String today;
    private String given;
    private Integer rentalId;

    public Integer getCopyId() {
        return copyId;
    }
    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUntil() {
        return until;
    }
    public void setUntil(String until) {
        this.until = until;
    }
    public String getToday() {
        return today;
    }
    public void setToday(String today) {
        this.today = today;
    }
    public String getGiven() {
        return given;
    }
    public void setGiven(String given) {
        this.given = given;
    }
    public Integer getRentalId() {
        return rentalId;
    }
    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    @Override
    public String toString() {
        return "SimpleRental{" +
                "bookId=" + copyId +
                ", userId=" + userId +
                ", today=" + today +
                ", given=" + given +
                ", rentalId=" + rentalId +
                ", until=" + until +
                '}';
    }
}
