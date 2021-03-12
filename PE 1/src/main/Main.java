package main;

import algoritmoGenetico.AlgoritmoGenetico;

public class Main {

	public static void main(String[] args) {
		AlgoritmoGenetico algon= new AlgoritmoGenetico(100,100,0.6,0.05,5);
		System.out.print("HOL");
		algon.run();
	}

}
