package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import common.Data;
import common.SessionGenerator;


public abstract class AbstractSessionGenerator<D extends Data> implements SessionGenerator {
	
	public AbstractSessionGenerator(
			Map<Integer, D> map,
			Registry reg,
			String exportName, // ++ sessionID as a suffix
			int port
	) throws RemoteException {
		this.map = map;
		this.reg = reg;
		this.exportName = exportName;
		this.port = port;
	}
	
	@Override
	public int getSessionID() throws RemoteException {
		//synchronized(lock) {
			D data = createEmptyData();
			D stub = (D )UnicastRemoteObject.exportObject(data, port);
			reg.rebind(exportName + sessionCounter, stub);
			map.put(sessionCounter, data);
			return sessionCounter++;
		//}
	}
	
	protected abstract D createEmptyData() throws RemoteException;
	
	//private Object lock;
	static int sessionCounter = 0;
	
	private Registry reg;
	private String exportName;
	private int port;
	protected Map<Integer, D> map;
}
