package utils;

import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import com.sun.prism.paint.Stop;

public class ThreadPooledServer implements Runnable{
	
	protected int				serverPort = 9090;
	protected ServerSocket	ServerSocket = null;
	protected boolean		isStopped = false;
	protected Thread			runningThread = null;
	protected ExecutorService ThreadPool = Executors.newFixedThreadPool(10);
	
	public ThreadPooledServer (int port) {
		this.serverPort = port;
	}
	
	private synchronized boolean isStopped() {
		return this.isStopped;
	}
	
	public synchronized void stop() {
		this.isStopped = true;
		try {
			this.ServerSocket.close();
		} catch (Exception e) {
			throw new RuntimeException("Error closing server", e);
		}
	}
	
	private void openServerSocket() {
		try {
			this.ServerSocket = new ServerSocket(this.serverPort);
		} catch (Exception e) {
			throw new RuntimeException("Cannot open port " + serverPort, e);
		}
	}
	

	@Override
	public void run() {
		synchronized(this){
			this.runningThread = Thread.currentThread();
		}
		
		openServerSocket();
		
		while(! isStopped()) {
			Socket clientSocket = null;
			try {
				clientSocket = this.ServerSocket.accept();
			} catch (Exception e) {
				if(isStopped()) {
					System.out.println("Server Stopped.") ;
                    break;
				}
				throw new RuntimeException("Error accepting client connection", e);
			}
		}
		
		this.ThreadPool.shutdown();
		System.out.println("Server Stopped.") ;
	}

}
