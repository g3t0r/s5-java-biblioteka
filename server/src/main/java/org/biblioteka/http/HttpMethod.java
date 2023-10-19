package org.biblioteka.http;

public enum HttpMethod {
    GET("GET"),
    POST("POST");

    HttpMethod(String name) {
        this.name = name;
    }

    private final String name;

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public static HttpMethod fromString(String s) {
        for(HttpMethod method: values()) {
            if(method.name.equalsIgnoreCase(s)) {
                return method;
            }
        }
        throw new IllegalArgumentException("No enum for value: " + s);
    }
}
