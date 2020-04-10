package httpserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHTTPServer {
    final HttpServer server;

    public SimpleHTTPServer(String hostname, int port) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(hostname, port), 0);
    }

    public void addEntry(String path, HTTPEntry entry) {
        this.server.createContext(path, entry);

    }

    public void startServer() {
        server.start();
    }

    public void stopServer() {
        server.stop(0);
    }
}

