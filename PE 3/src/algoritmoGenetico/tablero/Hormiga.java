package algoritmoGenetico.tablero;

import utils.Pair;

public class Hormiga { 
	private static int tamX,tamY;
	public enum Direccion {
		Norte, Este, Sur, Oeste
	};

	private int posX, posY;

	private Direccion direccion;

	public Hormiga() {
		tamX=tamY=32;
		direccion = Direccion.Este;
	}

	public void avanza() {
		switch (direccion) {
		case Norte:
			posX--;
			posX=check(posX);;
			break;
		case Este:
			posY++;
			posY=check(posY);
			break;
		case Sur:
			posX++;
			posX=check(posX);
			break;
		case Oeste:
			posY--;
			posY=check(posY);
			break;
		}
		
	}
	
	private int check(int pos) {
		if(pos==-1)
			return tamX-1;
		else return pos%tamX;
		
	}
	
	
	public Direccion getDir(){
		return direccion;
	}

	public Pair<Integer,Integer> getSigPos() {
		int x = posX;
		int y = posY;
		switch (direccion) {
		case Norte:
			x--;	
			x=check(x);
			break;
		case Este:
			y++;
			y=check(y);
			break;
		case Sur:
			x++;
			x=check(x);
			break;
		case Oeste:
			y--;
			y=check(y);
			break;
		}
		return new Pair<Integer,Integer>(x,y);
	}

	public void giraIzq() {
		int d = direccion.ordinal();
		if(d==0){
			d=3;
		}else{
			d--;
		}
		direccion = Direccion.values()[d];
		avanza();
	}

	public void giraDer() {
		int d = direccion.ordinal();
		if(d==3){
			d=0;
		}else{
			d++;
		}
		direccion = Direccion.values()[d];
		avanza();
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
