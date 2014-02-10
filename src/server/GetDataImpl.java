package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import common.Data;
import common.GetData;
import common.ITest;

public class GetDataImpl<D extends Data> implements GetData<D> {

	public GetDataImpl(Map<Integer, D> map) {
		this.map = map;
	}
	
	protected Map<Integer, D> map;

	@Override
	public D get(int sessionID) throws RemoteException {
		return map.get(sessionID);
	}

}
