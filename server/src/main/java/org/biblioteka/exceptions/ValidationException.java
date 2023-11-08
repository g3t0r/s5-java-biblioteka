package org.biblioteka.exceptions;

import org.biblioteka.http.HttpStatus;

public class ValidationException extends Http4xxException {
    public ValidationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
