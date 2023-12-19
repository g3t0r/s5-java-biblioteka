package org.biblioteka.shared.model;

public class AggregatedBooks {

    private String title;
    private String author;
    private String genre;
    private Integer total;
    private Integer available;

    public AggregatedBooks(String title, String author, String genre, Integer total, Integer available) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.total = total;
        this.available = available;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "AggregatedBooks{" +
                "author='" + author + '\'' +
                ", name='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", total=" + total +
                ", available=" + available +
                '}';
    }
}
