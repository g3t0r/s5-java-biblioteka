package org.biblioteka.http;

import com.google.gson.Gson;

import java.net.URI;
import java.util.Map;

public class JsonRequest<T> extends Request<T> {
    protected JsonRequest(HttpMethod method, URI uri, String protocol, Map<String, String> headers, T body) {
        super(method, uri, protocol, headers, body);
    }

    public static <T> JsonRequest<T> fromRawRequest(final RawRequest rawRequest, Class<T> type) {
        return new JsonRequest<>(rawRequest.getMethod(),
                rawRequest.getUri(),
                rawRequest.getProtocol(),
                rawRequest.getHeaders(),
                new Gson().fromJson(String.valueOf(rawRequest.getBody()), type));
    }
}
