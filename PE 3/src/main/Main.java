package main;


import javax.swing.SwingUtilities;

import controller.Controller;
import vista.MainWindow;


public class Main {

	public static void main(String[] args) {	
		Controller _ctrl= new Controller();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainWindow(_ctrl);
			}
		});
	}

}