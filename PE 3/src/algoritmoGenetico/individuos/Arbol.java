package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

public class Arbol {

	private String valor;
	private ArrayList<Arbol> hijos;
	private int numHijos;
	private int numNodos;
	private int max_prof;
	private int profundidad;
	private boolean useIF;
	private boolean esHoja;
	private boolean esRaiz;
	private String arrayArbol;
	
	public Arbol(String v) {
		valor=v;
		hijos=new ArrayList<Arbol>();
	}

	public Arbol(int max_prof2) {
		max_prof=max_prof2;
		hijos=new ArrayList<Arbol>();
	}

	public ArrayList<String> toArray(){
		ArrayList<String> array = new ArrayList<String>();
		toArrayAux(array, this);
		return array;
	}
	
	public String toString(){
		arrayArbol="Mejor("+String.valueOf(getAltura(getHijos(), 1))+"):";
		toStringAux(this);
		return arrayArbol;
	}
	
	private void toStringAux(Arbol a){
		arrayArbol+=a.valor;
		if(Individuo.esFuncion(a.valor)) arrayArbol+="(";
		else arrayArbol+=",";
		for(int i = 0; i < a.hijos.size(); i++){
			toStringAux(a.hijos.get(i));
		}
		if(Individuo.esFuncion(a.valor)) arrayArbol+="),";
	}
	
	// Insertar un valor en el arbol (nodo simple)
	public Arbol insert(String v, int index){
		Arbol a = new Arbol(v);
		if(index == -1){
			hijos.add(a);
			numHijos = hijos.size();
		}
		else
			hijos.set(index, a);
		return a;
	}
	
	// Insertar un arbol en otro arbol.
	public void insert(Arbol a, int index){
		if(index == -1){
			hijos.add(a);
			numHijos = hijos.size();
		}
		else
			hijos.set(index, a);
	}
	
	private void toArrayAux(ArrayList<String> array, Arbol a){
		array.add(a.valor);
		for(int i = 0; i < a.hijos.size(); i++){
			toArrayAux(array, a.hijos.get(i));
		}
	}
	
	public int getAltura(ArrayList<Arbol> hijoS, int n) {
		int alt = n;
		int aux;
		for(int i=0;i<hijoS.size();i++) {
			aux=getAltura(hijoS.get(i).getHijos(),n+1);
			if(aux>alt)alt=aux;
		}
		return alt;
	}
	
	public int inicializacionCompleta(int p, int nodos){
		int n = nodos;
		int nHijos = 2;
		if(p < max_prof){
			setProfundidad(p);
			Random rnd = new Random();
			int func = 0;			
			func = rnd.nextInt(Individuo.funciones.length);
			this.valor = Individuo.funciones[func];
			this.setEsRaiz(true);
			if(valor.equals("SIC"))nHijos = 2;
			else if(valor.equals("PROGN2")) nHijos = 2;
			else if(valor.equals("PROGN3")) nHijos = 3;
			for(int i = 0; i < nHijos; i++){
				Arbol hijo = new Arbol(max_prof);
				esRaiz = true;
				n++;
				n = hijo.inicializacionCompleta(p+1, n);
				hijos.add(hijo);
				numHijos++;
			}
			checkSIC(p);
			itrones();
		}
		else{
			setProfundidad(p);
			Random rnd = new Random();
			int terminal;
			this.setEsHoja(true);
			terminal = rnd.nextInt(Individuo.terminales6.length);
			valor = Individuo.terminales6[terminal];
			esHoja = true;
			numHijos = 0;
		}
		setNumNodos(n);
		return n;
	}
	
	public int inicializacionCreciente(int p, int nodos) {
		int n = nodos;
		int nHijos=0;
		if(p < max_prof){
			setProfundidad(p);
			Random rnd = new Random();
			int func = 0;
			int ini = new Random().nextInt(2);
			if(ini == 0 && p>0) {
				func = rnd.nextInt(Individuo.terminales6.length);
				this.valor = Individuo.terminales6[func];
				this.setEsHoja(true);
				numHijos = 0;
			}
			else {
				func = rnd.nextInt(Individuo.funciones.length);
				this.valor = Individuo.funciones[func];
				this.setEsRaiz(true);
			}	
			if(valor.equals("SIC"))nHijos = 2;
			else if(valor.equals("PROGN2")) nHijos = 2;
			else if(valor.equals("PROGN3")) nHijos = 3;
			for(int i = 0; i < nHijos; i++){
				Arbol hijo = new Arbol(max_prof);
				esRaiz = true;
				n++;
				if(valor.equals("SIC") && i== 0) {
					hijo.setValor("AVANZA");
					hijo.setNumHijos(0);
					hijo.setEsHoja(true);
				}
				else {					
					n = hijo.inicializacionCreciente(p+1, n);				
				}
				hijos.add(hijo);
				numHijos++;
			}
			
			checkSIC(p);
			itrones();
		}
		else{
			setProfundidad(p);
			Random rnd = new Random();
			int terminal;
			this.setEsHoja(true);
			terminal = rnd.nextInt(Individuo.terminales6.length);
			valor = Individuo.terminales6[terminal];
			esHoja = true;
			numHijos = 0;
		}
		setNumNodos(n);
		return n;
	}
	
	/**
	 * Comprobacion de que el primer hijo de SIC si es un terminal sea AVANZA o un funcion distina de SIC
	 */
	public void checkSIC(int p) {
		if(valor.equals("SIC") && hijos.get(1).getValor().equals("SIC") ) {
			hijos.get(1).setValor("PROGN2");
		}
		else if(valor.equals("SIC") && Individuo.esTerminal(hijos.get(1).getValor())) {
			if(hijos.get(1).getValor().equals("AVANZA")) {
				int aux = new Random().nextInt(2);
				if(aux==0)
					hijos.get(1).setValor("GIRA_DERECHA");
				else hijos.get(1).setValor("GIRA_IZQUIERDA");
			}
		}
		if(valor.equals("SIC") && hijos.get(0).getValor().equals("SIC") ) {
			hijos.get(0).setValor("PROGN2");
		}	
		else if(valor.equals("SIC") && Individuo.esTerminal(hijos.get(0).getValor())) {
			hijos.get(0).setValor("AVANZA");
		}
		
	}
	
	/**
	 * ELiminacion de lo nodos que tiene hijos que son intrones
	 */
	public void itrones() {
		if(valor.equals("PROGN2")) {
			if((hijos.get(0).getValor().equals("GIRA_DERECHA") && hijos.get(0).getValor().equals("GIRA_IZQUIERDA"))
					|| hijos.get(0).getValor().equals("GIRA_IZQUIERDA") && hijos.get(0).getValor().equals("GIRA_DERECHA")) {
				int aux = new Random().nextInt(2);
				if(aux==0)
					hijos.get(0).setValor("AVANZA");
				else hijos.get(1).setValor("AVANZA");
			}
		}
	}

	/**
	* Devuelve los nodos hoja del árbol
	* @param hijos Hijos del árbol a analizar
	* @param nodos Array donde se guardan los terminales
	*/
	public void getTerminales(ArrayList<Arbol> hijos, ArrayList<Arbol> nodos) {
		for(int i = 0; i < hijos.size(); i++){
			if(hijos.get(i).isEsHoja()){
				nodos.add(hijos.get(i).copia());
			}else{
				getTerminales(hijos.get(i).getHijos(), nodos);
			}
		}
	}
	
	public int insertTerminal(ArrayList<Arbol> list_hijos, Arbol terminal, int index, int pos){
		int p = pos;
		for(int i = 0; i < list_hijos.size() && p != -1; i++){
			if(list_hijos.get(i).isEsHoja() && (p == index)){
				list_hijos.set(i, terminal.copia());
				p = -1;
			}else if(list_hijos.get(i).esHoja && (p != index)){
				p++;
			}else{
				p = insertTerminal(list_hijos.get(i).hijos,terminal, index, p);
			}
		}
		return p;
	}
	
	public int insertFuncion(ArrayList<Arbol> list_hijos, Arbol terminal, int index, int pos){
		int p = pos;
		for(int i = 0; i < list_hijos.size() && p != -1; i++){
			if(list_hijos.get(i).esRaiz && (p == index)){
				list_hijos.set(i, terminal.copia());
				p = -1;
			}else if(list_hijos.get(i).esRaiz && (p != index)){
				p++;
				p = insertFuncion(list_hijos.get(i).hijos, terminal, index, p);
			}
		}
		return p;
	}
	
	public int insert(ArrayList<Arbol> list_hijos, Arbol terminal, int index, int pos){
		int p = pos;
		for(int i = 0; i < list_hijos.size() && p != -1; i++){
			if(p == index){
				list_hijos.set(i, terminal.copia());
				p = -1;
			}else if(p != index){
				p++;
				p = insertFuncion(list_hijos.get(i).hijos, terminal, index, p);
			}
		}
		return p;
	}

	/**
	* Devuelve los nodos internos del árbol
	* @param hijos Hijos del árbol a analizar
	* @param nodos Array donde se guardan las funciones
	*/
	public void getFunciones(ArrayList<Arbol> hijos, ArrayList<Arbol> nodos) {
		for(int i = 0; i < hijos.size(); i++){
			if(hijos.get(i).isEsRaiz()){
				nodos.add(hijos.get(i).copia());
				getFunciones(hijos.get(i).hijos, nodos);
			}
		}
	}
	
	
	public Arbol copia(){
		Arbol copia = new Arbol(this.max_prof/*, this.useIF*/);
		copia.setEsHoja(this.esHoja);
		copia.setEsRaiz(this.esRaiz);
		copia.setNumHijos(this.numHijos);
		copia.setNumNodos(this.numNodos);
		copia.setProfundidad(this.profundidad);
		copia.setValor(this.valor);
		ArrayList<Arbol> aux = new ArrayList<Arbol>();
		aux = copiaHijos();
		copia.setHijos(aux);
		return copia;
	}
	
	private ArrayList<Arbol> copiaHijos() {
		ArrayList<Arbol> array = new ArrayList<Arbol>();
		for(int i = 0; i < this.hijos.size(); i++){
			array.add(this.hijos.get(i).copia());
		}
		return array;
	}
	
	/**
	 * Obtiene el numero de nodos de un arbol
	 */
	public int obtieneNodos(Arbol nodo, int n){
		if(nodo.esHoja)
			return n;
		
		if(nodo.valor.equals("PROGN3")){
			n = obtieneNodos(nodo.hijos.get(0), n+1);
			n = obtieneNodos(nodo.hijos.get(1), n+1);
			n = obtieneNodos(nodo.hijos.get(2), n+1);
		}else if(nodo.valor.equals("PROGN2") || nodo.valor.equals("SIC")){
			n = obtieneNodos(nodo.hijos.get(0), n+1);
			n = obtieneNodos(nodo.hijos.get(1), n+1);
		}else{
			n = obtieneNodos(nodo.hijos.get(0), n+1);
		}
		return n;
	}
	
	public int profundidad(ArrayList<Arbol> hijoS, int p, int nodos) {
		int n= nodos;
		for(int i = 0; i < hijoS.size(); i++){
			hijoS.get(i).setProfundidad(p);
			n++;
			n= profundidad(hijoS.get(i).getHijos(),p+1, n);
			hijoS.get(i).setNumNodos(n);
		}
		
		return n;
	}
	
	
	//Setters y Getters//
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public ArrayList<Arbol> getHijos() {
		return hijos;
	}
	
	public Arbol getHijoAt(int index) {
		return hijos.get(index);
	}

	public void setHijos(ArrayList<Arbol> hijos) {
		this.hijos = hijos;
	}

	public int getNumHijos() {
		return numHijos;
	}

	public void setNumHijos(int numHijos) {
		this.numHijos = numHijos;
	}

	public int getNumNodos() {
		return numNodos;
	}

	public void setNumNodos(int numNodos) {
		this.numNodos = numNodos;
	}

	public int getMax_prof() {
		return max_prof;
	}

	public void setMax_prof(int max_prof) {
		this.max_prof = max_prof;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public boolean isUseIF() {
		return useIF;
	}

	public void setUseIF(boolean useIF) {
		this.useIF = useIF;
	}

	public boolean isEsHoja() {
		return esHoja;
	}

	public void setEsHoja(boolean esHoja) {
		this.esHoja = esHoja;
	}

	public boolean isEsRaiz() {
		return esRaiz;
	}

	public void setEsRaiz(boolean esRaiz) {
		this.esRaiz = esRaiz;
	}

}
