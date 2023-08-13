package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int THREAD_POOL_SIZE = 64;
    private static final int PORT = 9999;

    public static void main(String[] args) {
        try (final var serverSocket = new ServerSocket(PORT)) {
            ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

            while (true) {
                try {
                    final var socket = serverSocket.accept();
                    threadPool.execute(() -> ConnectionHandler.handleConnection(socket));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}