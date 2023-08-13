package org.example.server;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RequestHandler {
    private static final List<String> VALID_PATHS = List.of("/index.html", "/spring.svg", "/spring.png", "/resources.html", "/styles.css", "/app.js", "/links.html", "/forms.html", "/classic.html", "/events.html", "/events.js");

    public static void handleRequest(String path, BufferedOutputStream out) throws IOException {
        if (!VALID_PATHS.contains(path)) {
            ResponseUtils.sendNotFoundResponse(out);
        } else {
            final var filePath = Path.of(".", "public", path);
            final var mimeType = Files.probeContentType(filePath);

            if (path.equals("/classic.html")) {
                ResponseUtils.handleClassicHtml(out, filePath, mimeType);
            } else {
                ResponseUtils.handleRegularFile(out, filePath, mimeType);
            }
        }
    }
}