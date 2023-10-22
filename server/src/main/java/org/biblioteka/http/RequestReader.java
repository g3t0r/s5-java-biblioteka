package org.biblioteka.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RequestReader {

    private final BufferedReader reader;

    public RequestReader(BufferedReader reader) {
        this.reader = reader;
    }

    public RawRequest readRequest() throws IOException {

        String method;
        String uri;
        String protocol;
        Map<String, String> headers = new HashMap<>();

        String line = reader.readLine();
        String[] headerLineSegments = line.split(" ");
        if (headerLineSegments.length != 3) {
            throw new UnsupportedOperationException("Unsupported header line: " + line);
        }

        method = headerLineSegments[0];
        uri = headerLineSegments[1];
        protocol = headerLineSegments[2];

        for (line = reader.readLine(); line != null; line = reader.readLine()) {
            if (line.isEmpty()) {
                break;
            }
            String[] segments = line.split(": ");
            headers.put(segments[0], segments[1]);
        }
        if (headers.containsKey("Content-Length")) {
            int contentLength = Integer.parseInt(headers.get("Content-Length"));
            char[] data = new char[contentLength];
            int readBytes = reader.read(data);

            if (readBytes != contentLength) {
                throw new IllegalStateException(String.format("Read bytes (%d) are not equal content length (%d)",
                        readBytes, contentLength));
            }

            return new RawRequest(HttpMethod.fromString(method), URI.create(uri), protocol, headers, data);
        }

        return new RawRequest(HttpMethod.fromString(method), URI.create(uri), protocol, headers, null);
    }
}
