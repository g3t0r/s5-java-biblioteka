package org.biblioteka.http;

import java.net.URI;
import java.util.Map;

public class RawRequest extends Request<char[]>{
    public RawRequest(HttpMethod method, URI uri, String protocol, Map<String, String> headers, char[] body) {
        super(method, uri, protocol, headers, body);
    }
}
