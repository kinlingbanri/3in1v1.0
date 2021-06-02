package utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpoint;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.apache.tomcat.jdbc.pool.DisposableConnectionFacade;

@ClientEndpoint
public class WebSocketUtil {
	protected WebSocketContainer container;
	protected Session clientSession = null;
	
	public WebSocketUtil() {
		container = ContainerProvider.getWebSocketContainer();
	}
	
	public void Connect(String serverURl) {
		try {
			clientSession = container.connectToServer(this,  new URI(serverURl));
		} catch (DeploymentException | URISyntaxException | IOException e) {
			// TODO: handle exception
		}
	}
	
	public void Disconnect() throws IOException{
		clientSession.close();
	}
	
	public void SendMessage(String msg) throws IOException{
		clientSession.getBasicRemote().sendText(msg);
	}
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Connected");
		System.out.println("getDefaultMaxSessionIdleTimeout : " + session.getContainer().getDefaultMaxSessionIdleTimeout());
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("Close");
	}
	
	@OnMessage
	public void onMessage(Session session, String msg) {
		System.out.println("onMessage");
		System.out.println("msg : " + msg);
	}	
	
}
