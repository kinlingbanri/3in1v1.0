package utils;

import java.util.Arrays;
import java.util.List;

public class SerialPortMessage {

	public void SendMessage(String comport, String number, String Content) {
		long startTime=System.currentTimeMillis();

		//status == 0,success; tatus == 1, false
		
		String hexHeaderAT = "41542B434D47533D22";		//AT+CMGS="
		String hexNumberTailAT = "220D";
		
		String hexNumberString =  "";
        for (int i = 0; i < number.length(); i++) {  
            int ch = number.charAt(i);
            String s4 = Integer.toHexString(ch);  
            hexNumberString = hexNumberString + s4;  
        }

		
		String hexSetNumberStr = hexHeaderAT + hexNumberString + hexNumberTailAT;
		System.out.println("hexSetNumberStr : " + hexSetNumberStr);
		//ProcessBuilder pb = new ProcessBuilder("D:\\GoProjects\\Demo\\Serial\\serial.exe", comport, hexSetNumberStr);
		//ProcessBuilder pb = new ProcessBuilder("D:\\GoProjects\\Demo\\Serial\\serial.exe", (comport + " " +  hexSetNumberStr));
		
		List<String> numberList = Arrays.asList("D:\\GoProjects\\Demo\\Serial\\serial.exe", comport, hexSetNumberStr);
		ProcessBuilder pb = new ProcessBuilder(numberList);
		
		pb.inheritIO(); // <-- passes IO from forked process.
		try {
		    Process p = pb.start(); // <-- forkAndExec on Unix
		    int status = p.waitFor(); // <-- waits for the forked process to complete.
		    System.out.println("prcocess status : " + status);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		
		
		
		String hexContentTailAT = "1A";
		String hexContentStr =  "";
        for (int i = 0; i < Content.length(); i++) {  
            int ch = Content.charAt(i);
            String s4 = Integer.toHexString(ch);  
            hexContentStr = hexContentStr + s4;  
        }
        
        String hexSetContentStr = hexContentStr + hexContentTailAT;
        System.out.println("hexSetContentStr : " + hexSetContentStr);

		//pb.command("D:\\GoProjects\\Demo\\Serial\\serial.exe", comport, number);
//        pb.command("D:\\GoProjects\\Demo\\Serial\\serial.exe", (comport + " " +  number));
		
		List<String> comportList = Arrays.asList("D:\\GoProjects\\Demo\\Serial\\serial.exe", comport, hexSetContentStr);
		pb.command(comportList);
		
		
		pb.inheritIO(); // <-- passes IO from forked process.
		try {
		    Process p = pb.start(); // <-- forkAndExec on Unix
		    int status = p.waitFor(); // <-- waits for the forked process to complete.
		    System.out.print("prcocess status : " + status);
		} catch (Exception e) {
		    e.printStackTrace();
		}


		long endTime=System.currentTimeMillis(); //獲取結束時間

		System.out.println("程式執行時間：" + (endTime - startTime) + "ms");
	}
}
