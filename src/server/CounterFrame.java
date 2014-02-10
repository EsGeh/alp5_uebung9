package server;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import common.Counter;

public class CounterFrame extends Frame implements ActionListener, WindowListener {
	
	public CounterFrame(Counter counter) {
		super();
		this.counter = counter;
		
		addWindowListener(this);	// listen for events on this window
		label = new Label("0");
		btnInc = new Button("inc");
		btnDec = new Button("dec");
		
		add(label, BorderLayout.NORTH);
		add(btnInc, BorderLayout.WEST);
		add(btnDec, BorderLayout.EAST);
		btnInc.addActionListener(this); // listen for events from the button
		btnDec.addActionListener(this); // listen for events from the button
		
		update();
	}
	
	public void actionPerformed(ActionEvent event) {
		switch( event.getActionCommand() ) {
			case "inc": {
				//JOptionPane.showMessageDialog(null, "inc received", "inc: ", JOptionPane.INFORMATION_MESSAGE);
				try {
					counter.inc();
				} catch (RemoteException e) {
					throw new RuntimeException("error while incrementing: " + e.getMessage());
				}
			} break;
			case "dec": {
				try {
					counter.dec();
				} catch (RemoteException e) {
					throw new RuntimeException("error while decrementing: " + e.getMessage());
				}
			} break;
		}
		update();
	}
	
	void update() {
		try {
			label.setText(Integer.toString(counter.get()));
		} catch (RemoteException e) {
			throw new RuntimeException("error while reading counter: " + e.getMessage());
		}
	}
	
	private Counter counter;
	
	private Label label;
	private Button btnInc;
	private Button btnDec;

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		//System.exit(0);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
