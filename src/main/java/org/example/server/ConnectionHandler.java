package org.example.server;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler {
    public static void handleConnection(Socket socket) {
        try (
                final var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                final var out = new BufferedOutputStream(socket.getOutputStream());
        ) {
            final var requestLine = in.readLine();
            final var parts = requestLine.split(" ");

            if (parts.length != 3) {
                // just close socket
                socket.close();
                return;
            }

            final var path = parts[1];
            RequestHandler.handleRequest(path, out);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
