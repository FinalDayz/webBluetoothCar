package webserial;

import httpserver.*;
import serial.SimpelSerial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WebSerial {
    public static void main(String[] args) {
        try {
            (new WebSerial()).run();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to run, exiting");
        }
    }

    private final String COM_PORT = "COM3";
    private final char DIRECTION_COMMAND = 'D';
    private final char SPEED_COMMAND = 'S';
    private final char STEER_COMMAND = 'O';

    public void run() throws IOException {

        if (!SimpelSerial.hasPortName(COM_PORT)) {
            System.err.println(COM_PORT + " does not exist, exiting...");
            return;
        }

        final SimpelSerial serial = new SimpelSerial(SimpelSerial.getPortByName(COM_PORT));
        serial.start();

        SimpleHTTPServer server = new SimpleHTTPServer("192.168.2.146", 80);
        server.startServer();

        server.addEntry(
                "/command/motor/direction",
                new SerialCommandEntry(DIRECTION_COMMAND, "direction", serial)
        );

        server.addEntry(
                "/command/motor/speed",
                new SerialCommandEntry(SPEED_COMMAND, "speed", serial)
        );

        server.addEntry(
                "/command/servo/steer",
                new SerialCommandEntry(STEER_COMMAND, "steer", serial)
        );

        try {
            server.addEntry("/", new FileEntry(
                    new File(WebSerial.class.getClassLoader().getResource("index.html").getFile())
            ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
