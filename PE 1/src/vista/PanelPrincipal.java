package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.Button;
import javax.swing.JSplitPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.Panel;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Choice;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

public class PanelPrincipal {

	private JFrame frmPanelprincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelPrincipal window = new PanelPrincipal();
					window.frmPanelprincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PanelPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPanelprincipal = new JFrame();
		frmPanelprincipal.setTitle("PanelPrincipal");
		frmPanelprincipal.setBounds(100, 100, 622, 507);
		frmPanelprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPanelprincipal.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel Panel_Control = new JPanel();
		frmPanelprincipal.getContentPane().add(Panel_Control);
		Panel_Control.setLayout(new BorderLayout(0, 0));
		
		JPanel Cabecera = new JPanel();
		Panel_Control.add(Cabecera, BorderLayout.NORTH);
		
		JLabel lblAjustes = new JLabel("Ajustes");
		Cabecera.add(lblAjustes);
		
		JPanel Ajustes = new JPanel();
		Panel_Control.add(Ajustes, BorderLayout.CENTER);
		Ajustes.setLayout(new GridLayout(10, 1, 0, 0));
		
		JPanel Seleccion = new JPanel();
		Ajustes.add(Seleccion);
		Seleccion.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTipoDeSeleccion = new JLabel("Tipo de Seleccion:");
		lblTipoDeSeleccion.setHorizontalAlignment(SwingConstants.CENTER);
		Seleccion.add(lblTipoDeSeleccion);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "TorneoDeter", "TorneoProbab", "Truncamiento", "Estocastico", "Restos"}));
		Seleccion.add(comboBox);
		
		JLabel lblTamaoDelTorneo = new JLabel("Tamaño del torneo:");
		lblTamaoDelTorneo.setHorizontalAlignment(SwingConstants.CENTER);
		Seleccion.add(lblTamaoDelTorneo);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(2, 2, 5, 1));
		Seleccion.add(spinner);
		
		JSeparator separator = new JSeparator();
		Ajustes.add(separator);
		
		JPanel Cruce = new JPanel();
		Ajustes.add(Cruce);
		Cruce.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTipoDeCruce = new JLabel("Tipo de cruce:");
		Cruce.add(lblTipoDeCruce);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Monopunto", "Aritmetico", "Uniforme", "BLX", "SBX"}));
		Cruce.add(comboBox_1);
		
		JCheckBox elistismo = new JCheckBox("Elistismo");
		Ajustes.add(elistismo);
		
		JPanel Grafica = new JPanel();
		frmPanelprincipal.getContentPane().add(Grafica);
	}

	/*protected void selecciona() {
		ChangeDialog dialog= new ChangeDialog((Frame) SwingUtilities.getWindowAncestor(this));
		
		int option= dialog.open(_map);
		if(option==1) {
			List<Pair<String, Weather>> ws = new ArrayList<>();
			ws.add(new Pair<String, Weather>(dialogWeather.getCaja1(), dialogWeather.getCaja2()));
			_ctrl.addEvent(new SetWeatherEvent(_time + dialogWeather.getTicks(), ws));
		}
	}*/
}
