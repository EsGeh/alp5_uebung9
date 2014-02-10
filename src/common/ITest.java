package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITest extends Remote {
	void testMethod() throws RemoteException;
}
