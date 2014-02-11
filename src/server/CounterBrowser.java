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

	@Override
	public void show() {
		System.out.println("CounterBrowser.show()");
		CounterFrame frame = new CounterFrame(counter);
		frame.setVisible(true);
	}
	
	private Counter counter;

	@Override
	public void init(Counter data) {
		counter = data;
	}

}
