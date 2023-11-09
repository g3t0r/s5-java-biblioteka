package org.biblioteka.http;

public enum HttpStatus {
    OK(200, "OK"),
    NO_CONTENT(204, "NO_CONTENT"),
    NOT_FOUND(404, "NOT_FOUND"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),
    UNAUTHORIZED(401, "Unauthorized");


    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
    private final int code;
    private final String message;

    public static HttpStatus fromStatusCode(int code) {
        for(HttpStatus status : values()) {
           if(status.code == code) {
               return status;
           }
        }
        throw new IllegalArgumentException("Unsupported status code: " + code);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}