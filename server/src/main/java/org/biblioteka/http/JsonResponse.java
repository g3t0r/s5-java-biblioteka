package org.biblioteka.http;

import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JsonResponse<T> extends Response<T> {
    protected JsonResponse(String protocol, HttpStatus status, Map<String, String> headers, T body) {
        super(protocol, status, headers, body);
        getHeaders().put("content-type", "application/json; charset=utf-8");
    }

    @Override
    public byte[] getRawBody() {
        if(getBody() == null) {
            return new byte[0];
        }
        String json = new Gson().toJson(getBody());
        return new String(json.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8);
    }

    public static JsonResponse<Void> noContent(String protocol) {
        return new JsonResponse<>(protocol, HttpStatus.NO_CONTENT, new HashMap<>(), null);
    }

    public static <T> JsonResponse<T> ok(String protocol, T body) {
        return new JsonResponse<>(protocol, HttpStatus.OK, new HashMap<>(), body);
    }

    public static <T> JsonResponse<T> buildByStatusAndBody(String protocol, HttpStatus status, T body) {
        return new JsonResponse<>(protocol, status, new HashMap<>(), body);
    }

}
