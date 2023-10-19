package org.biblioteka.thread;

import org.biblioteka.http.Request;
import org.biblioteka.http.RequestReader;
import org.biblioteka.http.Response;
import org.biblioteka.http.ResponseWriter;

import java.io.*;
import java.net.Socket;

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

            Request<String> request = new RequestReader(reader).readRequest(String::new);
            System.out.println(request);

            Response<String> response = Response.ok(request.getProtocol(), "Test test");
            PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()));
            new ResponseWriter(printWriter).writeResponse(response);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
