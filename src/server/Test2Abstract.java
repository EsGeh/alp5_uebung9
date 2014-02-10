package server;

import java.rmi.RemoteException;

import common.ITest;

public abstract class Test2Abstract implements ITest {

	public Test2Abstract() throws RemoteException {
	}

	@Override
	public void testMethod() throws RemoteException {
		//synchronized( lock ) { <-- does not work!
			System.out.println("Hallo Welt 2!");
			testAbstract();
		//}
	}
	
	public abstract void testAbstract() throws RemoteException;
	
	//private Object lock;
	static int value = 0;

}
