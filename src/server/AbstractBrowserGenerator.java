package server;

import java.rmi.RemoteException;
import java.util.Map;

import common.BrowserGenerator;
import common.Data;
import common.GUI;
import common.IncorrectSessionID;

public abstract class AbstractBrowserGenerator<Browser extends GUI<D>, D extends Data> implements BrowserGenerator<D> {

	public AbstractBrowserGenerator(Map<Integer, D> map) throws RemoteException{
		this.map = map;
	}

	@Override
	public GUI<D> get(int sessionID) throws IncorrectSessionID, RemoteException {
		D data = map.get(sessionID);
		if( data == null) {
			throw new IncorrectSessionID();
		}
		return createBrowser(data);
	}
	
	protected abstract Browser createBrowser(D data) throws RemoteException;
	
	private Map<Integer, D> map;
}
