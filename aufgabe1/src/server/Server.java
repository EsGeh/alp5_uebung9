package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import common.BrowserGenerator;
import common.Counter;
import common.GetData;
import common.ITest;
import common.SessionGenerator;

public class Server {
	
	/*public static void main(String[] args) throws RemoteException {
		int port = 8080;
		Map<Integer,Counter> cookieToCounter = new HashMap<Integer,Counter>();
		CounterBrowserGenerator browserGen = new CounterBrowserGenerator(cookieToCounter);
		CounterSessionGenerator sessionGen = new CounterSessionGenerator(cookieToCounter);
		
		//
		
		// 1. create a security Manager:
				if( System.getSecurityManager() == null ) {
					System.setSecurityManager(new SecurityManager());
				}
				//2. 
				SessionGenerator sessionGenStub = (SessionGenerator )UnicastRemoteObject.exportObject(sessionGen, port);
				BrowserGenerator<CounterBrowser> browserGenStub = (BrowserGenerator<CounterBrowser> )UnicastRemoteObject.exportObject(browserGen, port);
				Registry registry = LocateRegistry.getRegistry();
				registry.rebind("SessionGenerator",sessionGenStub);
				registry.rebind("BrowserGenerator", browserGenStub);
	}*/
	public static void main(String[] args) {
		Server server = null;
		try {
			server = new Server();
		}
		catch( Exception e ) {
			System.out.println("exception while creating remote objects: " + e.getMessage());
		}
		try {
			server.run("counter");
			System.out.println("running..." );
		}
		catch( Exception e ) {
			System.out.println("exception while installing remote objects: " + e.getMessage());
		}
	}
	
	public Server() throws RemoteException {
		port = 8080;
		reg = LocateRegistry.getRegistry();
		
		cookieToCounter = new HashMap<Integer,Counter>();
		browserGen = new CounterBrowserGenerator(cookieToCounter);
		sessionGen = new CounterSessionGenerator(
				cookieToCounter,
				reg,
				"Counter",
				port
			);
		getData = new GetDataImpl<Counter>(cookieToCounter);
	}
	
	public void run(String appName) throws RemoteException {
		// 1. create a security Manager:
		if( System.getSecurityManager() == null ) {
			System.setSecurityManager(new SecurityManager());
		}
		//2.
		
		/*test = new Test();
		Test2Abstract test2 = new Test2();
		testStub = (ITest )UnicastRemoteObject.exportObject(test, port);
		ITest test2Stub = (ITest )UnicastRemoteObject.exportObject(test2, port);
		reg.rebind("Test", testStub);
		reg.rebind("Test2", test2Stub);*/
		
		/*CounterImpl counter = new CounterImpl();
		Counter counterStub = (Counter )UnicastRemoteObject.exportObject(counter, port);
		registry.rebind("Counter", counterStub);*/
		
		sessionGenStub = (SessionGenerator )UnicastRemoteObject.exportObject(sessionGen, port);
		getDataStub = (GetData<Counter> )UnicastRemoteObject.exportObject(getData, port);
		browserGenStub = (BrowserGenerator<Counter> )UnicastRemoteObject.exportObject(browserGen, port);
		
		reg.rebind(appName + "SessionGenerator",sessionGenStub);
		reg.rebind(appName + "GetData", getDataStub);
		reg.rebind(appName + "BrowserGenerator", browserGenStub);
	}
	
	private int port;
	private Registry reg;
	
	private Map<Integer,Counter> cookieToCounter;
	private CounterBrowserGenerator browserGen;
	private CounterSessionGenerator sessionGen;
	private GetDataImpl<Counter> getData;
	private Test test;
	
	private SessionGenerator sessionGenStub;
	private GetData<Counter> getDataStub;
	private BrowserGenerator<Counter> browserGenStub;
	private ITest testStub;
}
