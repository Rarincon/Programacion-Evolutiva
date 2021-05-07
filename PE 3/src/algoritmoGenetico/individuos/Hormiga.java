package algoritmoGenetico.individuos;

import utils.Pair;

public class Hormiga { //REPASAR Y ADAPTAR
	private static int tamX,tamY;
	public enum Direccion {
		Norte, Sur, Este, Oeste
	};

	private int posX, posY;

	private Direccion direccion;

	public Hormiga() {
		
		direccion = Direccion.Este;
	}
	
	/*public static void setTamTablero(int x,int y){
		tamX=x;
		tamY=y;
	}*/

	public void avanza() {
		switch (direccion) {
		case Norte:
			posX--;
			posX=toroideX(posX);
			break;
		case Este:
			posY++;
			posY=toroideY(posY);
			break;
		case Sur:
			posX++;
			posX=toroideX(posX);
			break;
		case Oeste:
			posY--;
			posY=toroideY(posY);
			break;
		}
		
	}
	
	private int toroideX(int pos){	
		int res=pos;
		if(pos==-1){
			res=tamX-1;
		}else if (pos==tamX){
			res=0;
		}
		return res;
	}
	private int toroideY(int pos){	
		int res=pos;
		if(pos==-1){
			res=tamY-1;
		}else if (pos==tamY){
			res=0;
		}
		return res;
	}
	
	public Direccion getDir(){
		return direccion;
	}

	public int[] getSigPos() {
		int x = posX;
		int y = posY;
		switch (direccion) {
		case Norte:
			x--;	
			x=toroideX(x);
			break;
		case Este:
			y++;
			y=toroideY(y);
			break;
		case Sur:
			x++;
			x=toroideX(x);
			break;
		case Oeste:
			y--;
			y=toroideY(y);
			break;
		}
		return new int[] { x, y };
	}

	public void giraIzq() {
		int aux = direccion.ordinal();
		if(aux==0){
			aux=3;
		}else{
			aux--;
		}
		direccion = Direccion.values()[aux];
	}

	public void giraDer() {
		int aux = direccion.ordinal();
		if(aux==3){
			aux=0;
		}else{
			aux++;
		}
		direccion = Direccion.values()[aux];
	}

	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}

	public Pair<Integer,Integer> getPos() {
		return new Pair<Integer,Integer>(posX, posY);
	}

}
