package org.biblioteka.dto;

public class ErrorDTO {
    public Integer code;
    public String message;

    public String stacktrace;

    public ErrorDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public ErrorDTO(Integer code, String message, String stacktrace) {
        this.code = code;
        this.message = message;
        this.stacktrace = stacktrace;
    }
}
