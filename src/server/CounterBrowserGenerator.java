package server;

import java.rmi.RemoteException;
import java.util.Map;

import common.Counter;

public class CounterBrowserGenerator extends
		AbstractBrowserGenerator<CounterBrowser, Counter> {

	public CounterBrowserGenerator(Map<Integer, Counter> map) throws RemoteException {
		super(map);
	}

	@Override
	protected CounterBrowser createBrowser(Counter data) throws RemoteException {
		return new CounterBrowser();
	}

}
