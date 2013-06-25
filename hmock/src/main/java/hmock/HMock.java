package hmock;

import hmock.http.HttpMethod;

import org.eclipse.jetty.server.Server;

public class HMock {

	/* default port is 7357 (test) */
	public static final int DEFAULT_PORT = 7357;
	
	private static int port = DEFAULT_PORT;
	
	private static Server _mockServer;
	
	public static void setPort(int port) {
		
		HMock.port = port;
	}
	
	private synchronized static void ensureInitialized() {
		
		setupShutdownHook();
		setupServer();
	}
	
	private static void setupServer() {
		
		if (_mockServer != null) {
			return;
		}
		
		_mockServer = new Server(HMock.port);
		_mockServer.setThreadPool(new DaemonThreadPool());
		
		try {
			_mockServer.start();
		} catch (Exception e) {
			throw new HMockException("unable to start server", e);
		}
	}

	private static void setupShutdownHook() {
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				if (_mockServer == null) {
					return;
				}
				
				try {
					_mockServer.stop();
				} catch (Exception e) {
					//Yea just pring something to the console if jetty shutdown failed
					e.printStackTrace();
				}
			}
		}));
	}
	
	public static HttpMethod on() {
		
		return null;
	}
	
	/* instantiate this class does not make any sense */
	private HMock() {};
}
