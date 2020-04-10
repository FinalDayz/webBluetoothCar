import httpserver.*;
import serial.SimpelSerial;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        try {
            final SimpelSerial serial = new SimpelSerial(SimpelSerial.getPortNames()[0]);
            serial.start();

            SimpleHTTPServer server = new SimpleHTTPServer("localhost", 80);
            server.startServer();
            server.addEntry("/command/motor/direction", new HTTPEntry() {
                @Override
                public HTTPResponse handleRequest(Request request) {
                    if(request.getRequestType() != RequestType.POST)
                        return HTTPResponse.notFound();

                    String direction = request.getBodyParameters().getValue("direction");


                    serial.write(direction.getBytes());
                    return HTTPResponse.ok();
                }
            });

            server.addEntry("/hi", new FileEntry(
                    new File(main.class.getClassLoader().getResource("hi.html").getFile())
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
