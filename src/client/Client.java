package client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.BrowserGenerator;
import common.Counter;
import common.GUI;
import common.GetData;
import common.IncorrectSessionID;
import common.SessionGenerator;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client("localhost");
		try {
			client.run();
		}
		catch(Exception e) {
			System.out.println("could not create client: "+ e.getMessage());
		}
	}

	public Client(String host) {
		this.host = host;
		this.port = 8080;
	}
	
	public void run() throws AccessException, RemoteException, NotBoundException {
		// 1. create a security Manager:
		if( System.getSecurityManager() == null ) {
			System.setSecurityManager(new SecurityManager());
		}
		// 2.
		reg = LocateRegistry.getRegistry();
		//reg = LocateRegistry.getRegistry(host, port); <-- das folgende funktioniert nicht! ?
		
		/*ITest test = (ITest )reg.lookup("Test2");
		test.testMethod();*/

		int sessionID = 0;
		{
			SessionGenerator sessionGen = (SessionGenerator )reg.lookup("SessionGenerator");
			sessionID = sessionGen.getSessionID();
			System.out.println("sessionID: " + sessionID);
		}
		//Data data = (Data )reg.lookup("Counter");
		System.out.println("fetching data...");
		Counter data = null;
		{
			GetData<Counter> getData = (GetData<Counter> )reg.lookup("GetData");
			data = getData.get(sessionID);
		}
		System.out.println("fetching browser...");
		GUI<Counter> browser = null;
		try {
			
			BrowserGenerator<Counter> browserGen = (BrowserGenerator<Counter> )reg.lookup("BrowserGenerator");
			browser = browserGen.get(sessionID);
		}
		catch(IncorrectSessionID e) {
			System.out.println("invalid session id: " + e.getMessage());
		}
		browser.init(data);
		browser.show();
	}
	
	private String host;
	private int port;
	private int sessionID;
	private Registry reg;
}
