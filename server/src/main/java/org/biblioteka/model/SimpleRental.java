package org.biblioteka.model;

import java.time.LocalDate;

public class SimpleRental {
    public Integer bookId;
    public Integer userId;
    public String until;
    public String today;
    public String given;
    public Integer rentalId;

    @Override
    public String toString() {
        return "SimpleRental{" +
                "bookId=" + bookId +
                ", userId=" + userId +
                ", today=" + today +
                ", given=" + given +
                ", rentalId=" + rentalId +
                ", until=" + until +
                '}';
    }
}
