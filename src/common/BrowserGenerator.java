package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BrowserGenerator<D extends Data> extends Remote {
	GUI<D> get(int sessionID) throws IncorrectSessionID, RemoteException;
}
