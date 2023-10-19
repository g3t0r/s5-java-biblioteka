package org.biblioteka.http;

import java.io.PrintWriter;
import java.util.Map;

public class ResponseWriter {
    private final PrintWriter writer;

    public ResponseWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public void writeResponse(Response<?> response) {
        writer.println(String.join(" ",
                response.getProtocol(),
                String.valueOf(response.getStatus().getCode()),
                response.getStatus().getMessage()));

        int bodyLength = response.getRawBody().length;

        response.getHeaders().putIfAbsent(HttpHeaders.CONTENT_LENGTH.getName(), String.valueOf(bodyLength));

        for(Map.Entry<String, String> header: response.getHeaders().entrySet()) {
            writer.println(String.format("%s: %s", header.getKey(), header.getValue()));
        }

        writer.println();

        if(bodyLength != 0) {
            writer.write(response.getRawBody());
        }

        writer.flush();
        writer.close();
    }
}
