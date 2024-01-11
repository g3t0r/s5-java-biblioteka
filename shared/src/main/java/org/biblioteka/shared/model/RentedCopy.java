package org.biblioteka.shared.model;

public class RentedCopy {
    private Integer id;
    private Integer copyId;
    private String publisher;
    private Boolean available;
    private String title;
    private String author;
    private String rentedAt;
    private String rentedUntil;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCopyId() {
        return copyId;
    }

    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRentedAt() {
        return rentedAt;
    }

    public void setRentedAt(String rentedAt) {
        this.rentedAt = rentedAt;
    }

    public String getRentedUntil() {
        return rentedUntil;
    }

    public void setRentedUntil(String rentedUntil) {
        this.rentedUntil = rentedUntil;
    }
}
