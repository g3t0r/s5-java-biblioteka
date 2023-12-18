package org.biblioteka.shared.model;

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

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", stacktrace='" + stacktrace + '\'' +
                '}';
    }
}
