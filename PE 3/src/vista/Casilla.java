package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import algoritmoGenetico.tablero.Hormiga;
import algoritmoGenetico.tablero.Hormiga.Direccion;

public class Casilla extends JTextArea {
	private static final long serialVersionUID = 1L;
	private boolean tieneComida;
	private boolean haPasado;

	public Casilla(boolean tieneComida) {
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		this.setEditable(false);
		setForeground(Color.BLACK);			
		setFont(new Font("Monospace",Font.BOLD,10));
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		this.tieneComida = tieneComida;
		haPasado = false;
		cargarFondo();
	}

	private void cambiarColor(Color c) {
		setBackground(c);
		// Si el L&F lo tapa, usar esto
		/*
		 * putClientProperty("Synthetica.background", c);
		 * putClientProperty("Synthetica.background.alpha", 0.20f);
		 */
	}

	private void cargarFondo() {
		if (this.tieneComida) {
			if (this.haPasado) {
				cambiarColor(Color.GREEN);
			} else {
				cambiarColor(Color.BLUE);
			}
		} else {
			if (this.haPasado) {
				cambiarColor(Color.RED);
			} else {
				cambiarColor(Color.WHITE);
			}
		}
	}

	public void pasaLaHormiga(Hormiga hormiga) {
		this.haPasado = true;	
		cargarTexto(hormiga.getDir());			
		cargarFondo();
	}

	private void cargarTexto(Direccion dir) {
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
	}

}
