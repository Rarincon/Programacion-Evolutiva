package algoritmoGenetico.tablero;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Tablero {
	
	private boolean casillas[][];
	private int fil, cols;
	private int numComida;
	
	public Tablero() {
		cols=fil=32;
	}
	
	public void cargarMapa(String ruta){
		try {			
			File f = new File(ruta);
			String a = f.getAbsolutePath();
			Scanner s = new Scanner(new File(a));
			String b;
			//Mapa
			numComida=0;
			casillas=new boolean[fil][cols];	
			for(int i=0;i<fil;i++){
				for(int j=0;j<cols;j++){
					b=s.next();
					casillas[i][j]=comida(b);
					if(casillas[i][j]){
						numComida++;
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			
		}
	}
	
	private boolean comida(String a) {
		if(a.equals("#"))return true;
		else return  false;
	}
	
	public boolean getCasilla(int x, int y){
		return casillas[x][y];
	}
	
	public int getNumComida(){
		return numComida;
	}
	
	public void comer(int x, int y){
		casillas[x][y]=false;
	}
	
	public Tablero clone(){
		Tablero t= new Tablero();
		t.numComida=this.numComida;
		t.casillas=new boolean[fil][cols];
		for(int i=0;i<fil;i++){			
			for(int j=0;j<cols;j++){
				t.casillas[i][j]=casillas[i][j];
			}
		}	
		return t;
	}

}
