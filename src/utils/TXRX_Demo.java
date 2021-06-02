package utils;

import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class TXRX_Demo {
	
	public void list() {
		Enumeration ports = CommPortIdentifier.getPortIdentifiers();
		
		 while(ports.hasMoreElements()) {
			CommPortIdentifier commPortIdentifier = (CommPortIdentifier)ports.nextElement();
			System.out.println(commPortIdentifier.getName());
		}
	}
	
	public static void main(String[] args) throws Exception {
		new TXRX_Demo().list();
		
		//通过端口名称得到端口
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(args[0]);
		// points who owns the port and connection timeout
		SerialPort serialPort = (SerialPort) portIdentifier.open(args[0], 3000);
//		CommPort commPort = portIdentifier.open("COM8", 3000);
		
		if (serialPort instanceof SerialPort) {
			System.out.println("Open OK!");
		}
		// setup connection parameters    
        serialPort.setSerialPortParams(    
            9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		
        //String str = "connect";
        byte[] bs = args[1].getBytes();
        
        OutputStream out = serialPort.getOutputStream();
        out.write(bs);
        out.flush();
        out.close();
		serialPort.close();
        
	}

}
