package algoritmoGenetico.tablero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import algoritmoGenetico.individuos.Tipo;
import utils.Pair;

public class Tablero {
	
	private boolean casillas[][];
	private int fil, cols;
	private int numComida;
	
	public Tablero() {
		cols=fil=32;
	}
	
	public void cargarMapa(String ruta){
		try {			
			/*BufferedReader lector=new BufferedReader(new FileReader(ruta));
			//Filas
			String linea=lector.readLine();
			filas=Integer.parseInt(linea);
			//Columnas
			linea=lector.readLine();
			cols=Integer.parseInt(linea);*/
			File f = new File(ruta);
			String a = f.getAbsolutePath();
			Scanner s = new Scanner(new File(a));
			String b;
			//Mapa
			numComida=0;
			casillas=new boolean[fil][cols];	
			//String []lineaPartida;
			for(int i=0;i<fil;i++){
				//linea=lector.readLine();
				//lineaPartida=linea.split(" ");
				for(int j=0;j<cols;j++){
					b=s.next();
					casillas[i][j]=comida(b);
					if(casillas[i][j]){
						numComida++;
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			//Logger.getLogger("CP").severe("Problema al abrir el archivo del mapa.");			
		}
	}
	
	private boolean comida(String a) {
		if(a.equals("#"))return true;
		else return  false;
	}
	
	/*public Pair<Integer, Integer> posIni(){
		return new Pair<Integer,Integer>(Xini,Yini);
	}*/
	
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
