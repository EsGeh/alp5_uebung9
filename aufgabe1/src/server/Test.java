package server;

import java.rmi.RemoteException;

import common.ITest;

public class Test implements ITest {

	public Test() throws RemoteException {
	}

	@Override
	public void testMethod() throws RemoteException {
		System.out.println("Hallo test!");
	}
	
}
