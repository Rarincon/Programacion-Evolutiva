package algoritmoGenetico.tablero;

import utils.Pair;

public class Hormiga { //REPASAR Y ADAPTAR
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
	
	/*public static void setTamTablero(int x,int y){
		tamX=x;
		tamY=y;
	}*/

	public void avanza() {
		switch (direccion) {
		case Norte:
			posX--;
			posX=check(posX);//toroideX(posX);
			break;
		case Este:
			posY++;
			posY=check(posY);//toroideY(posY);
			break;
		case Sur:
			posX++;
			posX=check(posX);//toroideX(posX);
			break;
		case Oeste:
			posY--;
			posY=check(posY);//toroideY(posY);
			break;
		}
		
	}
	
	private int check(int pos) {
		if(pos==-1)
			return tamX-1;
		else return pos%tamX;
		
	}
	
	/*private int toroideX(int pos){	
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
	}*/
	
	public Direccion getDir(){
		return direccion;
	}

	public Pair<Integer,Integer> getSigPos() {
		int x = posX;
		int y = posY;
		switch (direccion) {
		case Norte:
			x--;	
			x=check(x);//toroideX(x);
			break;
		case Este:
			y++;
			y=check(y);//toroideY(y);
			break;
		case Sur:
			x++;
			x=check(x);//toroideX(x);
			break;
		case Oeste:
			y--;
			y=check(y);//toroideY(y);
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
