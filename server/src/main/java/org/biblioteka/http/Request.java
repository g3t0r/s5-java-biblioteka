package org.biblioteka.http;

import java.net.URI;
import java.util.Map;
import java.util.function.Function;

public class Request<T> {

    public Request(HttpMethod method, URI uri, String protocol, Map<String, String> headers, T body) {
        this.method = method;
        this.uri = uri;
        this.protocol = protocol;
        this.headers = headers;
        this.body = body;
    }

    private HttpMethod method;
    private URI uri;
    private String protocol;
    private Map<String, String> headers;
    private T body;

    public <R> Request<R> cast(Function<T, R> map) {
        return new Request<>(
                method,
                uri,
                protocol,
                headers,
                (body == null) ? null : map.apply(body));
    }

    @Override
    public String toString() {
        return "Request{" +
                "method=" + method +
                ", uri=" + uri +
                ", protocol='" + protocol + '\'' +
                ", headers=" + headers +
                ", data=" + body +
                '}';
    }

    public HttpMethod getMethod() {
        return method;
    }

    public URI getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public T getBody() {
        return body;
    }
}
