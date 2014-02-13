package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SessionGenerator extends Remote {
	int getSessionID() throws RemoteException;
}