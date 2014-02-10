package server;

import java.awt.Button;
import java.awt.Frame;
import java.rmi.RemoteException;
import java.util.Scanner;

import common.Counter;
import common.GUI;

public class CounterBrowser implements GUI<Counter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*public CounterBrowser(Counter data) {
		//counter = data;
	}*/

	@Override
	public void show() {
		System.out.println("CounterBrowser.show()");
		CounterFrame frame = new CounterFrame(counter);
		frame.setVisible(true);
		/*Frame frame = new Frame("Counter-Browser");
		frame.add(new Button("inc"));
		frame.add(new Button("dec"));
		
		frame.addWindowListener(this);
		frame.setVisible(true);*/
		//while(true) {
			//System.out.println(counter);
			/*Scanner in = new Scanner(System.in);
			int i = in.nextInt();
			try {
				counter.set(i);
			}
			catch(RemoteException e) {
				System.out.println("RemoteException while setting data!: " + e.getMessage());
			}*/
		//}
	}
	
	private Counter counter;

	@Override
	public void init(Counter data) {
		counter = data;
	}

}
