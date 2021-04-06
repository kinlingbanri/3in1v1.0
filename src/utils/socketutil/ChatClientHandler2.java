package utils.socketutil;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientHandler2 implements Runnable {

	  // runnable for each client thread
		
		private Socket client;
		private ChatServer2 server;
		private Scanner inputStream;
		
		public ChatClientHandler2(Socket client, ChatServer2 server) {
			this.client = client;
			this.server = server;
		}
			
		@Override
		public void run() {
			try {
				inputStream = new Scanner(client.getInputStream());
				while(true)
				{
					if(!inputStream.hasNext())
						return;
					String chatLine = inputStream.nextLine();
					System.out.println(client.getRemoteSocketAddress() + " said: " + chatLine);
					server.sendChatMessageToAll(chatLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
}
