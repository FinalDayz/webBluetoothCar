package serial;

import com.fazecast.jSerialComm.SerialPort;

public class SimpelSerial {
    private int baudRate;
    private SerialPort serialPort;

    public SimpelSerial(SerialPort serialPort) {
        this(serialPort, 9600);
    }

    public SimpelSerial(SerialPort serialPort, int baudRate) {
        this.baudRate = baudRate;
        this.serialPort = serialPort;
        serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        serialPort.setBaudRate(this.baudRate);
    }

    public void start() {
        serialPort.openPort();
    }

    public void write(byte[] content) {
        this.serialPort.writeBytes(content, content.length);
    }
    public void close() {
        serialPort.closePort();
    }

    public static boolean hasPortName(String name) {
        return getPortByName(name) != null;
    }

    public static SerialPort getPortByName(String name) {
        for(SerialPort port : getPorts()) {
            if(port.getSystemPortName().equals(name)) {
                return port;
            }
        }
        return null;
    }

    public static SerialPort[] getPorts() {
        return SerialPort.getCommPorts();
    }
}
