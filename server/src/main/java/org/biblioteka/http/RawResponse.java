package org.biblioteka.http;

import java.util.Map;

public class RawResponse extends Response<char[]> {
    protected RawResponse(String protocol, HttpStatus status, Map<String, String> headers, char[] body) {
        super(protocol, status, headers, body);
    }

    @Override
    public char[] getRawBody() {
        return getBody();
    }
}
