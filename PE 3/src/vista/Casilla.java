package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import algoritmoGenetico.tablero.Hormiga;
import algoritmoGenetico.tablero.Hormiga.Direccion;

public class Casilla extends JTextArea {
	
	private static final long serialVersionUID = 1L;
	private boolean comida;
	private boolean pisada;

	public Casilla(boolean Comida) {
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		this.setEditable(false);
		setForeground(Color.BLACK);			
		setFont(new Font("Monospace",Font.BOLD,10));
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		this.comida = Comida;
		pisada = false;
		cargarFondo();
	}

	private void cambiarColor(Color c) {
		setBackground(c);
	}

	private void cargarFondo() {
		if (comida) {
			if (pisada) {
				cambiarColor(Color.DARK_GRAY);
			} else {
				cambiarColor(Color.BLACK);
			}
		} else {
			if (pisada) {
				cambiarColor(Color.GRAY);
			} else {
				cambiarColor(Color.WHITE);
			}
		}
	}

	public void pasaLaHormiga() {
		pisada = true;	
		//cargarTexto(hormiga.getDir());			
		cargarFondo();
	}

	/*private void cargarTexto(Direccion dir) {
		switch(dir){
		case Norte:
			setText("\u2191");
			break;
		case Sur:
			setText("\u2193");
			break;
		case Este:
			setText("\u2192");
			break;
		case Oeste:				
			setText("\u2190");
			break;
		}
	}*/

}
