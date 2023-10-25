package org.biblioteka.exceptions;

import org.biblioteka.http.HttpMethod;
import org.biblioteka.http.HttpStatus;

public class NotFoundException extends Http4xxException {
    protected NotFoundException(String message, HttpStatus status) {
        super(message, status);
    }

    public static NotFoundException build(String uri, HttpMethod method) {
        return new NotFoundException("Not Found error on: " + method.getName() + " " + uri, HttpStatus.NOT_FOUND);
    }
}
