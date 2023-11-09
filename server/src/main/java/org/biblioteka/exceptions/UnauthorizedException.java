package org.biblioteka.exceptions;

import org.biblioteka.http.HttpStatus;

public class UnauthorizedException extends Http4xxException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
