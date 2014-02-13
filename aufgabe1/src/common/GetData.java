package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GetData<D extends Data> extends Remote {
	D get(int sessionID) throws RemoteException;
}