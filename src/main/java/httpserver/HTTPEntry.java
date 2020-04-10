package httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class HTTPEntry implements HttpHandler {

    public boolean log = true;

    public HTTPEntry() {

    }

    public void handle(HttpExchange exchange) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        RequestType type = RequestType.UNDEFINED;
        for(RequestType typeValue : RequestType.values()) {
            if(typeValue.toString().equals(exchange.getRequestMethod())) {
                type = typeValue;
            }
        }

        InputStream requestBodyStream =  exchange.getRequestBody();
        String requestBody = IOUtils.toString(requestBodyStream, "UTF-8");
        String URL = exchange.getRequestURI().toString();
        Request request = new Request(URL, requestBody, type);
        HTTPResponse response = this.handleRequest(request);

        if(log) {
            System.out.println("REQUEST ["+type + " " + URL + "] Response code: " + response.getResponseCode());
        }

        exchange.sendResponseHeaders(response.getResponseCode(), response.getResponseHTML().length());
        outputStream.write(response.getResponseHTML().getBytes());
        outputStream.flush();
        outputStream.close();
    }

    public abstract HTTPResponse handleRequest(Request request);
}

