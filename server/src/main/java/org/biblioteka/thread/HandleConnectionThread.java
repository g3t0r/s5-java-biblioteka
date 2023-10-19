package org.biblioteka.thread;

import org.biblioteka.dto.TestDto;
import org.biblioteka.http.*;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class HandleConnectionThread implements Runnable {

    private final Socket clientSocket;

    public HandleConnectionThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Hello from client thread: " + Thread.currentThread().threadId());
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new BufferedInputStream(clientSocket.getInputStream())));

            JsonRequest<TestDto> request = JsonRequest.fromRawRequest(new RequestReader(reader).readRequest(), TestDto.class);
            System.out.println(request);
            System.out.println(request);

            JsonResponse<?> response = JsonResponse.noContent(request.getProtocol());

            PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()));
            new ResponseWriter(printWriter).writeResponse(response);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
