package webserial;

import httpserver.HTTPEntry;
import httpserver.HTTPResponse;
import httpserver.Request;
import httpserver.RequestType;
import serial.SimpelSerial;

public class SerialCommandEntry extends HTTPEntry {

    final char command;
    final String postCommand;
    final SimpelSerial serial;

    public SerialCommandEntry(char command, String postCommand, SimpelSerial serial) {
        this.command = command;
        this.postCommand = postCommand;
        this.serial = serial;
    }

    @Override
    public HTTPResponse handleRequest(Request request) {
        if (request.getRequestType() != RequestType.POST)
            return HTTPResponse.notFound();

        String direction = request.getBodyParameters().getValue(this.postCommand);
        try {
            serial.write(buildCommand(this.command, direction));
            return HTTPResponse.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return HTTPResponse.internalServerError();
        }
    }

    private byte[] buildCommand(char command, String parameter) throws NumberFormatException {
        byte intParameter = Byte.parseByte(parameter);
        return new byte[]{(byte) command, intParameter};
    }
}
