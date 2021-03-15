package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


import controller.Controller;

public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	Controller _ctrl;
	
	public MainWindow(Controller con) {
		super("Algoritmos Geneticos");
		_ctrl= con;
		//this.setPreferredSize(new Dimension(1000,1000));
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(); //Panel General
		this.setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		
		ControlPanel control= new ControlPanel(_ctrl);
		DataPanel data= new DataPanel(_ctrl);
		GraphicsPanel graphics = new GraphicsPanel(_ctrl);
		mainPanel.add(control, BorderLayout.WEST);  //Panel de Botones/menu
		mainPanel.add(graphics, BorderLayout.EAST);
		mainPanel.add(data, BorderLayout.SOUTH);
		
	//this.setPreferredSize(new Dimension(500, 500));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}
}
