package utils.netty;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class ChatServerListener implements ServletContextListener {
	
	private ChatServerThread chatServerThread;
	private ChatServerSingle chatServerSingle;

    public ChatServerListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	chatServerThread.stop();
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	if(null == chatServerThread){
//			//新建线程类
//    		chatServerThread = new ChatServerThread();
//			//启动线程
//    		chatServerThread.start();
    		
    		chatServerSingle = new ChatServerSingle();

		}
    }
	
}
