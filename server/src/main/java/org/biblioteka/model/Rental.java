package org.biblioteka.model;

import java.time.LocalDate;

public class Rental {
    private Integer bookId;
    private Integer userId;
    private Integer rentalId;
    private LocalDate today;
    private LocalDate until;
    private LocalDate given;

    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getRentalId() {
        return rentalId;
    }
    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }
    public LocalDate getToday() {
        return today;
    }
    public void setToday(LocalDate today) {
        this.today = today;
    }
    public LocalDate getUntil() {
        return until;
    }
    public void setUntil(LocalDate until) {
        this.until = until;
    }
    public LocalDate getGiven() {
        return given;
    }
    public void setGiven(LocalDate given) {
        this.given = given;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + rentalId +
                ", when='" + today + '\'' +
                ", until='" + until + '\'' +
                ", given='" + given + '\'' +
                ", book='" + bookId + '\'' +
                ", user=" + userId +
                '}';
    }
}
