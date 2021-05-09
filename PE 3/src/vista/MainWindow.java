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
		GraphicsPanel graphics = new GraphicsPanel(_ctrl);
		BoardPanel board = new BoardPanel(_ctrl);		
		JPanel centro = new JPanel(); 
		centro.setLayout(new GridLayout(2,1));		
		centro.add(control);		
		
		mainPanel.add(board, BorderLayout.WEST);
		mainPanel.add(control, BorderLayout.CENTER);  
		mainPanel.add(graphics, BorderLayout.EAST);
		mainPanel.add(data, BorderLayout.SOUTH);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}
}

