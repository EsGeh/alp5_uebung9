package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Counter extends Data, Remote {

	public void inc() throws RemoteException;
	public void dec() throws RemoteException;
	
	public int get() throws RemoteException;
	public void set(int counter) throws RemoteException;
	
}