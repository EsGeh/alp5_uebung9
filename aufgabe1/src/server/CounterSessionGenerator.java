package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Map;

import common.Counter;

public class CounterSessionGenerator extends AbstractSessionGenerator<Counter> {

	public CounterSessionGenerator(
			Map<Integer,Counter> map,
			Registry reg,
			String exportName, // ++ sessionID as a suffix
			int port
	) throws RemoteException {
		super(map, reg, exportName, port);
	}

	@Override
	protected Counter createEmptyData() throws RemoteException {
		
		return new CounterImpl();
	}
	
	
}
