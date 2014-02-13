package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import sun.org.mozilla.javascript.ast.TryStatement;

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
		Scanner in = new Scanner(System.in);
		String host = null;
		String app = null;
		{
			System.out.print("enter URL: ");
			String[] input = in.nextLine().split(" ");
			if( input.length != 2)
			{
				System.out.println(input[0]);
				System.out.println(input[1]);
				System.out.println("syntax: host app");
				return;
			}
			host = input[0];
			app = input[1];
		}
		Client client = new Client(host);
		try {
			client.run(app);
		}
		catch(Exception e) {
			System.out.println("could not create client: "+ e.getMessage());
		}
	}

	public Client(String host) {
		this.host = host;
		this.port = 8080;
	}
	
	public void run(String appName) throws AccessException, RemoteException, NotBoundException {
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
			try {
				sessionID = fetchSessionID(reg, appName);
			}
			catch(IOException e) {
				System.out.println("error while reading from file: " + e.getMessage());
				return;
			}
			System.out.println("sessionID: " + sessionID);
		}
		//Data data = (Data )reg.lookup("Counter");
		System.out.println("fetching data...");
		Counter data = null;
		{
			GetData<Counter> getData = (GetData<Counter> )reg.lookup(appName + "GetData");
			data = getData.get(sessionID);
		}
		System.out.println("fetching browser...");
		GUI<Counter> browser = null;
		try {
			
			BrowserGenerator<Counter> browserGen = (BrowserGenerator<Counter> )reg.lookup(appName + "BrowserGenerator");
			browser = browserGen.get(sessionID);
		}
		catch(IncorrectSessionID e) {
			System.out.println("invalid session id: " + e.getMessage());
			return;
		}
		browser.init(data);
		browser.show();
	}
	
	private int fetchSessionID( Registry reg, String appName ) throws IOException, NotBoundException {
		int i = tryToLoad( appName );
		if( i == -1 ){
			SessionGenerator sessionGen = (SessionGenerator )reg.lookup(appName + "SessionGenerator");
			i = sessionGen.getSessionID();
			
			// memorize sessionID for future sessions:
			System.out.println("memorizing cookie for future sessions...");
			createCookie(appName, i);
		}
		return i;
	}
	
	private int tryToLoad( String appName ) throws IOException {
		int i = -1;
		Path path = Paths.get(cookieDir + "/" + appName);
		if( Files.exists(path)) {
			System.out.println("cookie found for " + appName);
			try(
				InputStream is = new FileInputStream( path.toString() ); 
				InputStream bufferedIS = new BufferedInputStream(is);
				ObjectInput input = new ObjectInputStream (bufferedIS);
				//input.close();
			)
			{
				i = (Integer )input.readObject();
			}
			catch (ClassNotFoundException e) {
				throw new IOException("error while loading a cookie: " + e.getMessage());
			}
		}
		return i;
	}
	
	private void createCookie( String appName, int sessionID ) throws IOException {
		Path path = Paths.get(cookieDir);
		Files.createDirectories( path ); // should create the cookies-dir, if not existing
		OutputStream file = new FileOutputStream(cookieDir + "/" + appName);
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput output = new ObjectOutputStream(buffer);
		output.writeObject( new Integer(sessionID) );
		output.close();
		System.out.println("cookie created!");
	}
	
	//private static String appName = "Counter";
	private static String cookieDir = "cookies";
	
	private String host;
	private int port;
	private int sessionID;
	private Registry reg;
}
