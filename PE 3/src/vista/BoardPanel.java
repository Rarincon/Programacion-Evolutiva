package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.math.plot.Plot2DPanel;

import algoritmoGenetico.AlgoritmoGenObserver;
import algoritmoGenetico.tablero.Tablero;
import controller.Controller;
import utils.Pair;

public class BoardPanel extends JPanel  implements AlgoritmoGenObserver{

	private static final long serialVersionUID = 1L;
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);	
	private static final int TAM=32;
	
	private Controller _ctrl;
	private Tablero tablero;
	private Casilla[][] casillas;
	private JPanel tab;
	private List<Pair<Integer,Integer>>camino;
	
	public BoardPanel(Controller c) {
		_ctrl=c;
		_ctrl.addObserver(this);
		initGUI();
	}
	
	public void initGUI() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Tablero", TitledBorder.LEFT, TitledBorder.TOP));
		
		camino = new ArrayList<Pair<Integer,Integer>>();
		tab = new JPanel();
	
		add(tab);
		loadTablero();
		paintTablero();

		
		setPreferredSize(new Dimension(600, 500));
	}
	
	public void loadTablero() {
		this.tablero = Controller.getTablero();
		casillas = new Casilla[TAM][TAM];
		for (int i = 0; i < TAM; i++) {
			for (int j = 0; j < TAM; j++) {
				casillas[i][j] = new Casilla(tablero.getCasilla(i, j));
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
		loadTablero();	
		camino= (List<Pair<Integer, Integer>>) stats.get("Recorrido");
		for(int i=0;i<camino.size();i++)
			casillas[camino.get(i).getFirst()][camino.get(i).getSecond()].pasaLaHormiga();		
		paintTablero();		
	}

	@Override
	public void reset() {
		loadTablero();
		paintTablero();				
	}

}
