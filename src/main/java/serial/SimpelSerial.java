package serial;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class SimpelSerial {
    private final String COMPort;
    private int baudRate;

    private SerialPort serialPort;

    public SimpelSerial(String COMPort) {
        this(COMPort, 9600);
    }

    public SimpelSerial(String COMPort, int baudRate) {
        this.COMPort = COMPort;
        this.baudRate = baudRate;

        serialPort = new SerialPort(COMPort);
    }

    public void start() throws SerialPortException {
        serialPort.openPort();
        serialPort.setParams(this.baudRate, 8, 1, 0);
    }

    public void write(String content) throws SerialPortException {
        this.serialPort.writeString(content);
    }

    public void write(byte[] content) throws SerialPortException {
        this.serialPort.writeBytes(content);
    }

    public void write(int content) throws SerialPortException {
        this.serialPort.writeInt(content);
    }

    public void close() throws SerialPortException {
        serialPort.closePort();
    }

    static String[] getPortNames() {
        return SerialPortList.getPortNames();
    }
}
