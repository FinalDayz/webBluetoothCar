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

    public static SerialPort[] getPortNames() {
        return SerialPort.getCommPorts();
    }
}
