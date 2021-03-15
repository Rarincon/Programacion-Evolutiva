package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controller.Controller;

public class ControlPanel extends JPanel implements ActionListener, ItemListener{ //Revisar las implementaciones
	
	private static final long serialVersionUID = 1L;
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);	

	private Controller _ctrl;
	private JButton run;
	private JComboBox<String> Seleccion, Cruce,Individuo;
	private JCheckBox Elitismo;
	private JSpinner poblacion, maxGeneracion, mutacion,elitismo;
	
	private String[] seleccion= { "Ruleta","Estocastico","Torneo Probabilistico", "Torneo Deterministico", "Truncamiento", "Restos"};
	private String[] cruce= {"Monopunto","Aritmetico","Uniforme" ,"BLX", "SBX"};
	private String[] indis= {"Funcion1","Funcion2","Funcion3" ,"Funcion4", "Funcion5"};
	
	
	public ControlPanel(Controller c) {
		_ctrl=c;

		InitGui();
		createControl();
	}
	
	
	
	private void InitGui() {
		Elitismo = new JCheckBox(); //Revisar como funciona
		run= new JButton("Run");
		poblacion = new JSpinner(new SpinnerNumberModel(100,10,500,1));
		maxGeneracion = new JSpinner(new SpinnerNumberModel(100,100,500,10));
		//mutacion = new JSpinner(new SpinnerNumberModel(100,100,500,10));
		Seleccion = SComboBox();
		Cruce= CComboBox();
		Individuo= IComboBox();
		elitismo = new JSpinner(new SpinnerNumberModel(0.03, 0.0, 0.5, 0.01));//REVISAR
		elitismo.setPreferredSize(new Dimension(65,25));
		run.addActionListener(this);
		run.setPreferredSize(new Dimension(100,30));
		
		//this.setPreferredSize(new Dimension(50,50)); //REVISAR
		
	}
	
	private void createControl() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Controles", TitledBorder.LEFT, TitledBorder.TOP));
		add(estructura("Individuo", Individuo));
		add(estructura("Poblacion", poblacion));
		add(estructura("Generaciones", maxGeneracion));
		//add(labelComponent("Crossover", crossoverSpinner));
		//add(labelComponent("Mutation", mutationSpinner));
		//add(labelComponent("Precision", precisionSpinner));
		//(labelComponent("Elitism", elitismToggle));
		//add(labelComponent("   Rate", elitismSpinner));
		add(estructura("Selection", Seleccion));
		add(estructura("Crossover", Cruce));
		
		
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));			
		buttons.add(run);
		add(buttons);
		this.setPreferredSize(new Dimension(450, 450));
		setVisible(true);
	}
	
	private JComboBox<String> SComboBox() {
		JComboBox<String> s = new JComboBox<String>();
		for (int i = 0; i < seleccion.length;i++) s.addItem(seleccion[i].toString());
		s.setPreferredSize(new Dimension(200,25));
		s.addItemListener(this);
		return s;
	}
	
	private JComboBox<String> CComboBox() {
		JComboBox<String> c = new JComboBox<String>();
		for (int i = 0; i < cruce.length;i++) c.addItem(cruce[i].toString());
		c.setPreferredSize(new Dimension(200,25));
		c.addItemListener(this);
		return c;
	}
	
	private JComboBox<String> IComboBox() {
		JComboBox<String> c = new JComboBox<String>();
		for (int i = 0; i < indis.length;i++) c.addItem(indis[i].toString());
		c.setPreferredSize(new Dimension(200,25));
		c.addItemListener(this);
		return c;
	}
	
	private JPanel estructura(String a, JComponent c) { //REVISAR PARA PODER HACERLO DE OTRA FORMA
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
		left.add(new JLabel(a + ":"));
		JPanel right = new JPanel(new FlowLayout(FlowLayout.LEFT));
		right.add(c);
		left.setPreferredSize(new Dimension(100,30));
		right.setPreferredSize(new Dimension(250,30));
		p.add(left);
		p.add(right);
		return p;
	}
	
	private void carga() {
		_ctrl.setIndi(Individuo.getSelectedIndex());
		_ctrl.setPob((int) poblacion.getValue());
		_ctrl.setMaxGen((int) maxGeneracion.getValue());
		//_ctrl.setPrec((double) precisionSpinner.getValue());
		//_ctrl.setProbCrossover((double) crossoverSpinner.getValue());
		//_ctrl.setProbMut((double) mutacion.getValue());
		//_ctrl.setElitism(elitismToggle.isSelected());
		//_ctrl.setEliteRate((double) elitismSpinner.getValue());
		_ctrl.setSelection(Seleccion.getSelectedIndex());//getSelectedItem().toString());
		_ctrl.setCruce(Cruce.getSelectedIndex()); //DEvolver String o entero, comprobar	
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == run) {
			carga();
			_ctrl.reset();
			_ctrl.run();
		}
	}
}
