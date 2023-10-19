package org.biblioteka.http;

import com.sun.net.httpserver.Headers;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class Response<T> {
    private String protocol;
    private HttpStatus status;
    private Map<String, String> headers;
    private T body;

    protected Response(String protocol, HttpStatus status, Map<String, String> headers, T body) {
        this.protocol = protocol;
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public static Response<Void> noContent(String protocol) {
        return new Response<>(protocol, HttpStatus.NO_CONTENT, Collections.emptyMap(), null);
    }

    public static <T> Response<T> ok(String protocol, T body) {
        return new Response<>(protocol,
                HttpStatus.OK,
                Collections.singletonMap("Content-Length", String.valueOf(body.toString().length())),
                body);
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

    public Response<char[]> cast(Function<T, char[]> mapping) {
        return new Response<>(protocol, status, headers, mapping.apply(body));
    }
}
