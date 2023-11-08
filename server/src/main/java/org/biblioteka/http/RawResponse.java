package org.biblioteka.http;

import java.util.Map;

public class RawResponse extends Response<byte[]> {
    protected RawResponse(String protocol, HttpStatus status, Map<String, String> headers, byte[] body) {
        super(protocol, status, headers, body);
    }

    @Override
    public byte[] getRawBody() {
        return getBody();
    }
}
