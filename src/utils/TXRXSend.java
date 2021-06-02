package utils;

import java.io.OutputStream;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class TXRXSend {

	public void sendStr(String phone, String comPort, byte[] data) throws Exception {
		//通过端口名称得到端口
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(comPort);
		
		// points who owns the port and connection timeout
		SerialPort serialPort = (SerialPort) portIdentifier.open(comPort, 3000);
		
		if (serialPort instanceof SerialPort) {
			System.out.println("Open OK!");
		}
		
		// setup connection parameters    
        serialPort.setSerialPortParams(    
            9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        
        OutputStream out = serialPort.getOutputStream();
        out.write(data);
        out.flush();
        out.close();
		serialPort.close();
	}
}
