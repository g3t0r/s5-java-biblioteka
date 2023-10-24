package org.biblioteka.exceptions;

import org.biblioteka.http.HttpStatus;

public class Http4xxException extends RuntimeException {


    public Http4xxException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public static Http4xxException badRequest(String message) {
        return new Http4xxException(message, HttpStatus.BAD_REQUEST);
    }


    public HttpStatus getStatus() {
        return status;
    }

    protected Http4xxException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    private final HttpStatus status;
}
