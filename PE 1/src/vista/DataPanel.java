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
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import algoritmoGenetico.AlgoritmoGenObserver;
import controller.Controller;

public class DataPanel extends JPanel implements AlgoritmoGenObserver{ 
	
	private static final long serialVersionUID = 1L;
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);	
	private JLabel media, ValoresA,ValoresM,FitnessM,FitnessA;
	
	private double FitnessMejor,Media,FitnessActul;
	private List<Double> FitnessMejorV,FitnessActualV;

	private Controller _ctrl;
	
	public DataPanel(Controller c) {
		_ctrl=c;
		FitnessMejorV= new ArrayList<Double>();
		FitnessActualV=new ArrayList<Double>();
		createData();
		_ctrl.addObserver(this);
	}
	
	
	private void createData() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Soluciones", TitledBorder.LEFT, TitledBorder.TOP));
	
		/*labels = new ArrayList<JLabel>();
		for (int i = 0; i < _stats.length;++i) {
			JLabel l = new JLabel(_stats[i] + ":  ---");
			l.setPreferredSize(new Dimension(400, 15));
			_labels.add(l);
			p.add(l);
		}*/
		/*msg= new JLabel("Eventos");
		time= new JLabel("0");
		this.add(new JLabel("Time: "));
        this.add(time);
        this.add(msg);*/
		
		this.setPreferredSize(new Dimension(80, 80));
		setVisible(true);
	}


	@Override
	public void update(int generation, Map<String, Object> stats) {
		/*FitnessMejorV.add((double)stats.get("Mejor Objetivo Valores"));
		Media=(double) stats.get("Media");
		FitnessMejor=  (double) stats.get("Objetivo");
		FitnessActul =   (double) stats.get("Mejor Actual");*/
		//for (int i = 0; i < stats.length;++i) 	_labels.get(i).setText(_stats[i] + ":   " + stats.get(_stats[i]).toString());
	}


	@Override
	public void reset() {
		 
	}
	
}
