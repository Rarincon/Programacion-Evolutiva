package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


import controller.Controller;

public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	Controller _ctrl;
	
	public MainWindow(Controller con) {
		super("Algoritmos Geneticos");
		_ctrl= con;
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(); 
		this.setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		
		ControlPanel control= new ControlPanel(_ctrl);
		DataPanel data= new DataPanel(_ctrl);
		//WritePanel write= new WritePanel(_ctrl);
		//ReadPanel read = new ReadPanel(_ctrl);
		GraphicsPanel graphics = new GraphicsPanel(_ctrl);
		mainPanel.add(control, BorderLayout.WEST);  
		mainPanel.add(graphics, BorderLayout.EAST);
		JPanel sur = new JPanel(); 
		sur.setLayout(new GridLayout(0,3));		
		sur.add(data);
		//sur.add(write);
		//sur.add(read);
		mainPanel.add(sur, BorderLayout.SOUTH);
		
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}
}

