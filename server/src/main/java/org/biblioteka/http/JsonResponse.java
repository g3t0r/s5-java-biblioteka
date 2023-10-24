package org.biblioteka.http;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class JsonResponse<T> extends Response<T> {
    protected JsonResponse(String protocol, HttpStatus status, Map<String, String> headers, T body) {
        super(protocol, status, headers, body);
    }

    @Override
    public char[] getRawBody() {
        if(getBody() == null) {
            return new char[0];
        }
        return new Gson().toJson(getBody()).toCharArray();
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
