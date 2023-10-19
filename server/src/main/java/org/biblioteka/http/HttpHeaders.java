package org.biblioteka.http;

public enum HttpHeaders {
    CONTENT_LENGTH("Content-Length");

    HttpHeaders(String name) {
        this.name = name;
    }
    private final String name;

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
