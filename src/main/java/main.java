import httpserver.*;
import jssc.SerialPortException;
import serial.SimpelSerial;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        try {
            final SimpelSerial serial = new SimpelSerial("COM3");
            serial.start();

            SimpleHTTPServer server = new SimpleHTTPServer("localhost", 80);
            server.startServer();
            server.addEntry("/command/motor/direction", new HTTPEntry() {
                @Override
                public HTTPResponse handleRequest(Request request) {
                    if(request.getRequestType() != RequestType.POST)
                        return HTTPResponse.notFound();

                    String direction = request.getBodyParameters().getValue("direction");

                    try {
                        serial.write(direction);
                        return HTTPResponse.ok();

                    } catch (SerialPortException e) {
                        e.printStackTrace();
                        return HTTPResponse.internalServerError();
                    }
                }
            });

            server.addEntry("/hi", new FileEntry(
                    new File(main.class.getClassLoader().getResource("hi.html").getFile())
            ));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
}
