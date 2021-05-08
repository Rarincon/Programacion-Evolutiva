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
	
	public Arbol(String v) {
		valor=v;
		hijos=new ArrayList<Arbol>();
	}

	public Arbol(int max_prof2) {
		//profundidad=0;
		max_prof=max_prof2;
		hijos=new ArrayList<Arbol>();
		//useIF=useIF2;
	}

	// Devuelve el arbol en forma de array
	public ArrayList<String> toArray(){
		ArrayList<String> array = new ArrayList<String>();
		toArrayAux(array, this);
		return array;
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
	
	public Arbol at(int index){
		return at(this, 0, index);
	}
	
	private Arbol at(Arbol a, int pos, int index){
		Arbol s = null;
		if(pos >= index) s = a;
		else if(a.getNumHijos() > 0){
			for(int i = 0; i < a.getNumHijos(); i++)
				if(s == null) s = at(a.getHijos().get(i), pos+i+1, index);
		}
		return s;
	}
	
	private void toArrayAux(ArrayList<String> array, Arbol a){
		array.add(a.valor);
		for(int i = 0; i < a.hijos.size(); i++){
			toArrayAux(array, a.hijos.get(i));
		}
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
				//hijo.setPadre(this);
				esRaiz = true;
				n++;
				n = hijo.inicializacionCompleta(p+1, n);
				hijos.add(hijo);
				numHijos++;
			}
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
			//else nHijos = 0;
			for(int i = 0; i < nHijos; i++){
				Arbol hijo = new Arbol(max_prof);
				//hijo.setPadre(this);
				esRaiz = true;
				n++;
				n = hijo.inicializacionCreciente(p+1, n);
				hijos.add(hijo);
				numHijos++;
			}
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

	private int creaHijos(int p, int nodos) { //ADAPTAR
		int n = nodos;
		int nHijos = 2;
		if(valor.equals("IF")) nHijos = 3;
		if(valor.equals("NOT")) nHijos = 1;
		for(int i = 0; i < nHijos; i++){
			Arbol hijo = new Arbol(max_prof/*, useIF*/);
			//hijo.setPadre(this);
			n++;
			//n = hijo.inicializacionCrecienteAux(p+1, n);
			hijos.add(hijo);
			numHijos++;
		}
		return n;
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
				//terminal.padre = list_hijos.get(i).padre;
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
				//terminal.padre = list_hijos.get(i).padre;
				list_hijos.set(i, terminal.copia());
				p = -1;
			}else if(list_hijos.get(i).esRaiz && (p != index)){
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
	
	public int obtieneNodos(Arbol nodo, int n){ //REPASAR PARA ADAPTAR LA FUNCION A NUESTROS TERMINALES
		if(nodo.esHoja)
			return n;
		
		if(nodo.valor.equals("IF")){
			n = obtieneNodos(nodo.hijos.get(0), n+1);
			n = obtieneNodos(nodo.hijos.get(1), n+1);
			n = obtieneNodos(nodo.hijos.get(2), n+1);
		}else if(nodo.valor.equals("AND") || nodo.valor.equals("OR")){
			n = obtieneNodos(nodo.hijos.get(0), n+1);
			n = obtieneNodos(nodo.hijos.get(1), n+1);
		}else{
			n = obtieneNodos(nodo.hijos.get(0), n+1);
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
