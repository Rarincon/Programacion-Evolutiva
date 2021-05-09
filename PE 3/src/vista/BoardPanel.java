package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.math.plot.Plot2DPanel;

import algoritmoGenetico.AlgoritmoGenObserver;
import algoritmoGenetico.tablero.Tablero;
import controller.Controller;

public class BoardPanel extends JPanel  implements AlgoritmoGenObserver{

	private static final long serialVersionUID = 1L;
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);	
	private static final int TAM=32;
	
	private Controller _ctrl;
	private Tablero tablero;
	private Casilla[][] casillas;
	private JPanel tab;
	
	public BoardPanel(Controller c) {
		_ctrl=c;
		_ctrl.addObserver(this);
		initGUI();
	}
	
	public void initGUI() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Tablero", TitledBorder.LEFT, TitledBorder.TOP));
		
		JPanel panel = new JPanel(); 
		/*plot = new Plot2DPanel();
		plot.setAxisLabel(0, " Generaciones");
		plot.setAxisLabel(1, " Fitness");
		plot.addLegend("SOUTH");
		
		Generaciones= new ArrayList<Double>();
		MejorActual= new ArrayList<Double>();
		Media= new ArrayList<Double>();
		Objetivo= new ArrayList<Double>();*/
		
		add(panel);
		
		setPreferredSize(new Dimension(600, 500));
	}
	
	public void loadTablero(Tablero tab) {
		this.tablero = Controller.getTablero();
		casillas = new Casilla[mapa.getNumFilas()][mapa.getNumCols()];
		for (int i = 0; i < mapa.getNumFilas(); i++) {
			for (int j = 0; j < mapa.getNumCols(); j++) {
				casillas[i][j] = new Casilla(mapa.getCasilla(i, j));
			}
		}

	}
	
	public void paintTablero() {
		tab.removeAll();
		tab.setLayout(new java.awt.GridLayout(TAM,TAM));
		for (int i = 0; i < TAM; i++) {
			for (int j = 0; j < TAM; j++) {
				tab.add(casillas[i][j]);
			}
		}

	}

	@Override
	public void update(int generation, Map<String, Object> stats) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
