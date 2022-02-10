package ee.ut.cs.ds.sample.rpc;

import java.io.IOException;

public interface RPCServer {
	
	public void stop();
	
	public void start() throws IOException;
	
	public void register(Class serviceInterface, Class impl);
	
	public boolean isRunning();
	
	public int getPort();

}
