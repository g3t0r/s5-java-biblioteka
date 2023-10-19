package org.biblioteka;

import org.biblioteka.http.Response;
import org.biblioteka.thread.HandleConnectionThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello from the server");

        Executor executorService = Executors.newFixedThreadPool(10);
        ServerSocket server = new ServerSocket(2020);
        while(true) {
           Socket clientSocket =  server.accept();
           executorService.execute(new HandleConnectionThread(clientSocket));
        }
    }
}