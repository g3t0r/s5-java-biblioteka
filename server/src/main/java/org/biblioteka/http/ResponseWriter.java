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

        for(Map.Entry<String, String> header: response.getHeaders().entrySet()) {
            writer.println(String.format("%s: %s", header.getKey(), header.getValue()));
        }

        writer.println();

        if(response.getBody() instanceof String stringResponse) {
            writer.write(stringResponse);
        }
        writer.flush();
        writer.close();
    }
}
