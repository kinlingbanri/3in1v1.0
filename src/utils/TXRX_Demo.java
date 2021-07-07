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
		long startTime=System.currentTimeMillis();
		
		new TXRX_Demo().list();
		
		String comportName = "COM9";
		String phoneNumber = "0987654321";
		String data = "4154490D0A";
		
		//通过端口名称得到端口
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(comportName);
		// points who owns the port and connection timeout
		SerialPort serialPort = (SerialPort) portIdentifier.open(comportName, 3000);
//		CommPort commPort = portIdentifier.open("COM8", 3000);
		
		if (serialPort instanceof SerialPort) {
			System.out.println("Open OK!");
		}
		// setup connection parameters    
        serialPort.setSerialPortParams(    
            9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		
        //String str = "connect";
        byte[] bs = data.getBytes();
        
        OutputStream out = serialPort.getOutputStream();
        out.write(bs);
        out.flush();
        out.close();
		serialPort.close();
		
		long endTime=System.currentTimeMillis(); //獲取結束時間		
		System.out.println("程式執行時間：" + (endTime - startTime) + "ms"); 
        
	}

}
