package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Rectangle;
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
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import algoritmoGenetico.AlgoritmoGenObserver;
import controller.Controller;

public class DataPanel extends JPanel implements AlgoritmoGenObserver{ 
	
	private static final long serialVersionUID = 1L;
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);	
	//private static final String[] paneles = {"Media", "Objetivo", "Objetivo Genotipos", "Mejor Actual", "Mejor Actual Genotipos"};
	private JLabel fit,med,abec, descif,cruc, mut;
	static private final String abecedario="a b c d e f g h i j k l m n o p q r s t u v w x y z";
	static private final int TAM= 26;
	
	private Controller _ctrl;
	private double fitness,media;
	private String descifrado;
	
	private int cruces, mutaciones;
	
	public DataPanel(Controller c) {
		_ctrl=c;
		fitness=0;
		media=0;
		descifrado= "";
		
		cruces=0;
		mutaciones=0;
		//Arrays.fill(descifrado,"");
		createData();
		_ctrl.addObserver(this);
	}
	
	
	private void createData() {		
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new BorderLayout());
		//setLayout(new FlowLayout(FlowLayout.CENTER));
		setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Solucion", TitledBorder.LEFT, TitledBorder.TOP));
		
		JPanel p = new JPanel();
		//p.setLayout(new GridLayout(4,1,20,75));
		p.setLayout(new GridLayout(6,1));
		p.setPreferredSize(new Dimension(50,50));
		
		//p.setBorder(BorderFactory.createTitledBorder(_defaultBorder, "", TitledBorder.LEFT, TitledBorder.TOP));
		JLabel x= new JLabel("Cromosoma");
		x.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		x.setVerticalAlignment(SwingConstants.CENTER);
		JPanel x0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		x0.add(x);
		String aux= "Fitness: "+ String.valueOf(fitness);
		fit = new JLabel(aux);
		JPanel x1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		x1.add(fit);
		abec = new JLabel(abecedario);
		JPanel x2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		x2.add(abec);
		descif= new JLabel(descifrado);
		JPanel x3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		x3.add(descif);
		String aux1= "Media: "+ String.valueOf(media);
		med = new JLabel(aux1);
		JPanel x4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		x4.add(med);
		
		String aux2= "N. Cruces: "+ String.valueOf(cruces);
		cruc = new JLabel(aux2);
		JPanel x5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		x5.add(cruc);
		String aux3= "N. Mutaciones: "+ String.valueOf(mutaciones);
		mut = new JLabel(aux3);
		JPanel x6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		x6.add(mut);
		
		JPanel r = new JPanel(new GridLayout(1,2));
		r.add(x5);
		r.add(x6);
		
		//cruc.setVerticalAlignment(SwingConstants.LEFT);
		//mut.setVerticalAlignment(SwingConstants.LEFT);
		
		fit.setVerticalAlignment(SwingConstants.CENTER);
		med.setVerticalAlignment(SwingConstants.CENTER);
		abec.setVerticalAlignment(SwingConstants.CENTER);
		descif.setVerticalAlignment(SwingConstants.CENTER);
		
		JSeparator sep= new JSeparator(SwingConstants.HORIZONTAL);
		sep.setPreferredSize(new Dimension(10,50));
		sep.setForeground(Color.black);
		
		//p.add(x);
		p.add(x1);
		p.add(x4);
		p.add(r);	//Comentan 
		p.add(x2);
		p.add(sep);
		p.add(x3);
		
		//Chapucilla
		add(x0, BorderLayout.NORTH);
		add(new JLabel("                 "), BorderLayout.WEST);
		add(p, BorderLayout.CENTER);
		add(new JLabel("                 "), BorderLayout.EAST);
		add(new JLabel(" "), BorderLayout.SOUTH);
		setPreferredSize(new Dimension(200, 130));
	}


	@Override
	public void update(int generation, Map<String, Object> stats) {
		
		cruces = (int) stats.get("Num Cruces");
		mutaciones = (int) stats.get("Num Mutaciones");
		String aux2= "N. Cruces: "+ String.valueOf(cruces);
		String aux3= "N. Mutaciones: "+ String.valueOf(mutaciones);
		cruc.setText(aux2);
		mut.setText(aux3);
		
		fitness= (double) stats.get("fitness");
		media= (double) stats.get("Media");
		descifrado=(String) stats.get("Conversion");
		String aux= "Fitness: "+ String.valueOf(fitness);
		String aux1= "Media: "+ String.valueOf(media);
		fit.setText(aux);
		med.setText(aux1);
		descif.setText(descifrado);
		
	}

	@Override
	public void reset() {		
		fit.setText("Fitness: --");
		med.setText("Media: --");
		descif.setText("");
		
		cruc.setText("N. Cruces: --");
		mut.setText("N. Mutaciones: --");
	}
	
}

