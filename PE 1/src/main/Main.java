package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.SwingUtilities;

import algoritmoGenetico.AlgoritmoGenetico;
import controller.Controller;
import vista.MainWindow;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.SwingUtilities;

import algoritmoGenetico.AlgoritmoGenetico;
import controller.Controller;
import vista.MainWindow;

public class Main {

	public static void main(String[] args) {	
		Controller _ctrl= new Controller();
		//_ctrl.run();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainWindow(_ctrl);
			}
		});
		
		//System.out.print("HOLA que tal");
		//algon.run();
	}

}