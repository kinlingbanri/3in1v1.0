package utils;

public class TXRX_Demo2 {

	public static void main(String[] args) throws Exception {
		long startTime=System.currentTimeMillis();

		SerialPortMessage serialPortMessage = new SerialPortMessage();
		
		
		serialPortMessage.SendMessage("COM9", "0935276906", "password:127Tdf0jl^0^!@#$");
		
		/*
		//	status == 0,success
		// status == 1, false
				ProcessBuilder pb = new ProcessBuilder("D:\\GoProjects\\Demo\\Serial\\serial.exe", "41542B434D47533D2230393335323736393036220D");
				pb.inheritIO(); // <-- passes IO from forked process.
				try {
				    Process p = pb.start(); // <-- forkAndExec on Unix
				    int status = p.waitFor(); // <-- waits for the forked process to complete.
				    System.out.print("prcocess status : " + status);
				} catch (Exception e) {
				    e.printStackTrace();
				}
				
				
				pb.command("D:\\GoProjects\\Demo\\Serial\\serial.exe", "3C50617373776F72643A3931326B776572212A213E1A");
				pb.inheritIO(); // <-- passes IO from forked process.
				try {
				    Process p = pb.start(); // <-- forkAndExec on Unix
				    int status = p.waitFor(); // <-- waits for the forked process to complete.
				    System.out.print("prcocess status : " + status);
				} catch (Exception e) {
				    e.printStackTrace();
				}
		 */
				long endTime=System.currentTimeMillis(); //獲取結束時間
				
				System.out.println("程式執行時間：" + (endTime - startTime) + "ms"); 
				
        
	}

}
