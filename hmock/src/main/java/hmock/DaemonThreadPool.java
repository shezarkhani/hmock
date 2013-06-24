package hmock;

import org.eclipse.jetty.util.thread.QueuedThreadPool;

class DaemonThreadPool extends QueuedThreadPool {

	{
		super.setDaemon(true);
	}
	
	@Override
	public boolean isDaemon() {
		
		return true;
	}
	
	@Override
	public void setDaemon(boolean daemon) {
		
		throw new UnsupportedOperationException("Threads created by this pool is always daemon thread");
	}
	
}
