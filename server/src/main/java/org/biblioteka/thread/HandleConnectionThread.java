package org.biblioteka.thread;

import org.biblioteka.auth.AuthenticationExtractor;
import org.biblioteka.auth.UserAuthInfo;
import org.biblioteka.dto.ErrorDTO;
import org.biblioteka.exceptions.Http4xxException;
import org.biblioteka.http.*;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

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

            RawRequest rawRequest = new RequestReader(reader).readRequest();

            try {

                UserAuthInfo authInfo = AuthenticationExtractor.extractAuthInfo(rawRequest);
                RequestContext<?> context = new RequestContext<>(rawRequest, authInfo);
                ContextHolder.getInstance().putContext(context);
                System.out.println(context);

                JsonResponse<?> response = JsonResponse.noContent(rawRequest.getProtocol());
                PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()));
                new ResponseWriter(printWriter).writeResponse(response);

            } catch (Http4xxException e) {
                sendErrorResponse(rawRequest.getProtocol(), e.getStatus(), e.getMessage(), null);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                sendErrorResponse(rawRequest.getProtocol(), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), sw.toString());
                e.printStackTrace();
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendErrorResponse(String protocol, HttpStatus status, String message, String stacktrace) {
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()));
            ErrorDTO dto = new ErrorDTO(status.getCode(), message, stacktrace);
            JsonResponse<?> errorResponse = JsonResponse.buildByStatusAndBody(protocol, status, dto);
            new ResponseWriter(printWriter).writeResponse(errorResponse);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
