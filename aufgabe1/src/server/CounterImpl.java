package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.Counter;

public class CounterImpl implements Counter,Remote {
	
	public CounterImpl() throws RemoteException {
		counter = 0;
	}
	@Override
	public void inc() throws RemoteException {
		counter++;
	}
	@Override
	public void dec() throws RemoteException {
		counter--;
	}
	
	@Override
	public int get() throws RemoteException {
		return counter;
	}
	
	@Override
	public void set(int counter) throws RemoteException {
		this.counter = counter;
	}
	
	
	private int counter;
}