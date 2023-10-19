package org.biblioteka.http;

import java.util.Map;

public abstract class Response<T> {
    private final String protocol;
    private final HttpStatus status;
    private final Map<String, String> headers;
    private final T body;

    protected Response(String protocol, HttpStatus status, Map<String, String> headers, T body) {
        this.protocol = protocol;
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public String getProtocol() {
        return protocol;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public T getBody() {
        return body;
    }

    public abstract char[] getRawBody();
}
