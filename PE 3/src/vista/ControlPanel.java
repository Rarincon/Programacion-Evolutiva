package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import algoritmoGenetico.AlgoritmoGenetico;
import controller.Controller;

public class ControlPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);	

	private Controller _ctrl;
	private boolean _stopped;
	private JButton run,stop,reset;
	private JComboBox<String> Seleccion,Inicializacion, Mutacion;
	private JSpinner poblacion, maxGeneracion, mutacion,elitismo,probCruce,torneo;
	
	private JSlider Speed;
	
	private String[] seleccion= { "Ruleta","Estocastico","Torneo Probabilistico", "Torneo Deterministico", "Truncamiento", "Restos","Ranking"};
	private String[] mutac= {"Terminal Simple","Arbol","Permutacion","Funcion Simple","Contraccion","Expansion","Hoist"};
	private String[] inicial= { "Completa","Creciente","Ramped&Half"};
	
	private Map<String, String> datos;
	
	public ControlPanel(Controller c) {
		_ctrl=c;
		_stopped=false;
		InitGui();
		createControl();
	}
		
	private void InitGui() {

		poblacion = new JSpinner(new SpinnerNumberModel(100,15,500,1));
		poblacion.setPreferredSize(new Dimension(75,30));
		maxGeneracion = new JSpinner(new SpinnerNumberModel(100,10,1000,10));
		maxGeneracion.setPreferredSize(new Dimension(75,30));
		mutacion = new JSpinner(new SpinnerNumberModel(0.05, 0.0, 1.0, 0.01));
		mutacion.setPreferredSize(new Dimension(75,30));
		elitismo = new JSpinner(new SpinnerNumberModel(0.03, 0.0, 0.5, 0.01));
		elitismo.setPreferredSize(new Dimension(75,30));
		torneo = new JSpinner(new SpinnerNumberModel(5, 2, 14, 1));
		torneo.setPreferredSize(new Dimension(75,30));
		probCruce= new JSpinner(new SpinnerNumberModel(0.6, 0.0, 1.0, 0.05));
		probCruce.setPreferredSize(new Dimension(75,30));

		Seleccion = ComboBox(seleccion);
		Mutacion=ComboBox(mutac);
		Inicializacion=ComboBox(inicial);
		
		Speed= new JSlider(JSlider.HORIZONTAL,0 ,10, 5);
		Speed.setPreferredSize(new Dimension(250,45));
		Speed.setMaximumSize(new Dimension(250, 45));
		Speed.setMinimumSize(new Dimension(150, 45));
		Speed.setInverted(true);
		Speed.setToolTipText("Speed of the Simulation");
		Hashtable<Integer,JLabel> labelTable = new Hashtable<Integer,JLabel>();
		labelTable.put(new Integer(0), new JLabel("Fast"));
		labelTable.put(new Integer(5), new JLabel("Medium"));
		labelTable.put(new Integer(10), new JLabel("Slow"));
		Speed.setLabelTable( labelTable );
		Speed.setMajorTickSpacing(5);
		Speed.setMinorTickSpacing(1);
		Speed.setPaintTicks(true);
		Speed.setPaintLabels(true);
				
		//AlgoritmoGenetico.loadDataFile(loadData());
		
		run= new JButton("Run");
		run.setToolTipText("Run/Resume");
		run.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	if(_ctrl.getGenAct()==0) {
		    		carga();
		    		_ctrl.run();
			    	setEnable(false);
		    	}
				_stopped=false;
				run.setEnabled(false);
				reset.setEnabled(false);
				maxGeneracion.setEnabled(false);
				int ticks= (int) maxGeneracion.getValue()-_ctrl.getGenAct();
				run_sim(ticks);
		    }
			}); 
		run.setPreferredSize(new Dimension(75,30));
		
		stop= new JButton("Stop");
		stop.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
				_stopped=true;
		    }
			}); 
		stop.setPreferredSize(new Dimension(75,30));
		
		reset= new JButton("Reset");
		reset.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
				reset();
				setEnable(true);
		    }
			}); 
		reset.setPreferredSize(new Dimension(75,30));
	}
	
	private void setEnable(boolean b) {
		poblacion.setEnabled(b);
		mutacion.setEnabled(b);
		elitismo.setEnabled(b);
		torneo.setEnabled(b);
		Seleccion.setEnabled(b);
		mutacion.setEnabled(b);
		probCruce.setEnabled(b);
		Mutacion.setEnabled(b);
		Inicializacion.setEnabled(b);
	}
	
	private void reset() {
		_ctrl.reset();
		_stopped=false;
	}
	
	private void run_sim(int n) {
		if (n > 0 && !_stopped ){
			try {
				Thread.sleep(Speed.getValue()*50);
				_ctrl.run_sim(); 
			} catch (Exception e) {
				System.out.print(e);
				return;
			}
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					run_sim(n - 1);
				}
			});
		}
		else {
			_stopped =false;
			run.setEnabled(true);
			reset.setEnabled(true);
			maxGeneracion.setEnabled(true);
		}
	}
	
	private void createControl() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Controles", TitledBorder.LEFT, TitledBorder.TOP));
		
		add(estructura("Poblacion", poblacion));
		add(estructura("Generaciones", maxGeneracion));
		add(estructura("Inicializacion", Inicializacion));
		add(estructura("Elitismo", elitismo));
		add(estructura("Seleccion", Seleccion));
		add(estructura("Mutacion", Mutacion));
		add(estructura("Tam. Torneos", torneo));
		add(estructura("Prob. Cruce", probCruce));
		add(estructura("Prob. Mutacion", mutacion));
		add(Speed);
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));			
		buttons.add(estructura3(run,stop,reset));
		add(buttons);
		this.setPreferredSize(new Dimension(280, 450));
		setVisible(true);
	}
	
	private JComboBox<String> ComboBox(String[] a) {
		JComboBox<String> s = new JComboBox<String>();
		for (int i = 0; i < a.length;i++) s.addItem(a[i].toString());
		s.setPreferredSize(new Dimension(125,30));
		return s;
	}
		
	private JPanel estructura(String a, JComponent c) { 
		JPanel p = new JPanel(new GridLayout(1,2,10,10));
		JPanel up = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel l= new JLabel(a);
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		up.add(l);
		JPanel down = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		down.add(c);
		p.add(up);
		p.add(down);
		return p;
	}
	
	private JPanel estructura3(JComponent a, JComponent b, JComponent c) { 
		JPanel r = new JPanel(new GridLayout(1,3));
		r.add(a);
		r.add(b);
		r.add(c);
		return r;
	}
	
	private void carga() {
		_ctrl.setPob((int) poblacion.getValue());
		_ctrl.setMaxGen((int) maxGeneracion.getValue());	
		_ctrl.setInic(Inicializacion.getSelectedIndex());
		_ctrl.setElitismRango((double) elitismo.getValue());
		_ctrl.setSelection(Seleccion.getSelectedIndex());
		_ctrl.setTamTorneo((int) torneo.getValue());
		_ctrl.setMutac(Mutacion.getSelectedIndex());
		_ctrl.setProbCruce((double) probCruce.getValue());
		_ctrl.setProbMut((double) mutacion.getValue());
	}
	
	/*private Object[] loadData() {
		File f = new File("resources/ngrams/");
		datos = new HashMap<String, String>();
		for (File fil : f.listFiles()) {
			if (fil.getName().substring(fil.getName().lastIndexOf('.'), fil.getName().length()).equalsIgnoreCase(".txt")) {
				datos.put(fil.getName(), fil.getAbsolutePath());
			}
		}
		Object[] list = datos.values().toArray();
		Arrays.sort(list);
		return list;
	}*/

}
