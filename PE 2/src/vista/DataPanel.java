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
	private static final String[] paneles = {"Media", "Objetivo", "Objetivo Genotipos", "Mejor Actual", "Mejor Actual Genotipos"};
	private List<JLabel> labels;

	private Controller _ctrl;
	
	public DataPanel(Controller c) {
		_ctrl=c;
		createData();
		_ctrl.addObserver(this);
	}
	
	
	private void createData() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Soluciones", TitledBorder.LEFT, TitledBorder.TOP));
		JPanel p = new JPanel();
		labels = new ArrayList<JLabel>();
		for (int i = 0; i < paneles.length;i++) {
			JLabel a = new JLabel(paneles[i]);
			a.setPreferredSize(new Dimension(1000, 15));
			labels.add(a);
			p.add(a);
		}
		
		p.setVisible(true);
		p.setPreferredSize(new Dimension(500, 200));
		this.add(p);
		this.setPreferredSize(new Dimension(200, 130));
		setVisible(true);
	}


	@Override
	public void update(int generation, Map<String, Object> stats) {
		
		for (int i = 0; i < paneles.length;i++)
			labels.get(i).setText(paneles[i] + ":   " + stats.get(paneles[i]).toString());
	}


	@Override
	public void reset() {
		for (int i = 0; i < paneles.length;i++)
			labels.get(i).setText(paneles[i]);
	}
	
}

