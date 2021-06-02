package utils;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class WebSocketClientDemo {
	//public WebSocketUtil webSocketUtil;
	public WebSocketClientDemo(){
		
	}
	


	public static void main(String[] args) throws IOException {
		
		JSONArray BootNotification = new JSONArray();
		BootNotification.put(2);													// message type
		BootNotification.put("a93f730d-e53d-4ea5-8185-68833b2f7116");	// UUID
		
		JSONObject bootNotificationObject = new JSONObject();
		bootNotificationObject.put("chargePointModel", "13623");
		bootNotificationObject.put("chargePointVendor", "echoSocket");
		
		BootNotification.put(bootNotificationObject);

		WebSocketUtil webSocketUtil = new WebSocketUtil();
		webSocketUtil.Connect("ws://52.198.10.218:8091/OCPP/echoSocket/00001");
		System.out.println(BootNotification.toString()); 
		webSocketUtil.SendMessage(BootNotification.toString());
		webSocketUtil.SendMessage(BootNotification.toString());
		webSocketUtil.Disconnect();
	}

}
