package server;

import java.rmi.RemoteException;

public class Test2 extends Test2Abstract {

	public Test2() throws RemoteException {
	}

	@Override
	public void testAbstract() throws RemoteException {
		System.out.println("abstract Method!");
	}

}
