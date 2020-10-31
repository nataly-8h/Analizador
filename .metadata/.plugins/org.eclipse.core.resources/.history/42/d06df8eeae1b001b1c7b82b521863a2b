import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;

public class Analizador {
	public String[] gramatica;
	public ArrayList<String> terminals,
							 genOrder;
	public Hashtable<String, String> generators;
	
	public Hashtable<String, ArrayList<String>> grammar;
	

	public Analizador(String gramatic) {
		int generadores = 0;
		this.gramatica = gramatic.split("");
		this.generators = new Hashtable<String, String>();
		this.terminals = new ArrayList<String>();
		this.genOrder = new ArrayList<String>();
		
		//obtencion de elementos terminales y generadores de la gramatica
		for(int i = 0; i < gramatica.length -1; i++) {
			if(gramatica[i+1].equals("→")) {
				this.generators.put(gramatica[i], gramatica[i]);
				this.genOrder.add( gramatica[i]);
			}else if(!gramatica[i].equals("→") && !generators.contains(gramatica[i]) && !gramatica[i].equals("|") && !gramatica[i].isBlank()) {
				this.terminals.add(gramatica[i]);
			}
		}
		
		if(!gramatica[gramatica.length -1].equals("→") && !generators.contains(gramatica[gramatica.length -1]) && !gramatica[gramatica.length -1].equals("|") && !gramatica[gramatica.length -1].isBlank()) {
			this.terminals.add(gramatica[gramatica.length -1]);
		}
		
		//Acomodo de gramatica en arraylist
		this.grammar = new  Hashtable<String, ArrayList<String>>();
		System.out.println(Arrays.toString(this.gramatica));
		//int generated = -1; // para que comience en 0
		String currentG = "";
		String expression = "";
		int expr = 0; //expresiones de cada gramatica
		
		for(int i = 0; i < gramatica.length -1; i++) {
			if(gramatica[i+1].equals("→")) {
				this.grammar.put(gramatica[i], new ArrayList<String>());
				currentG = gramatica[i];
			}else {
				if(gramatica[i].contentEquals("|") || gramatica[i].isBlank()) {
					this.grammar.get(currentG).add(expression);
					expression = "";
				}else {
					if(!gramatica[i].equals("→")) {
						expression = expression + gramatica[i];
					}
					
				}
			}
		}
		expression = expression + gramatica[gramatica.length -1];
		this.grammar.get(currentG).add(expression);

		System.out.println(this.grammar.toString());
		
		inutiles();
		
	}
	
	public void inutiles() {
		
		//conexiones
		
		//Nodo[] conexiones = new Nodo[this.genOrder.size()];
		Hashtable<String, Nodo> conexiones = new Hashtable<String, Nodo>();
		for(int i = 0; i<this.genOrder.size(); i++) {
			if(i==0) {
				conexiones.put(this.genOrder.get(i), new Nodo(this.genOrder.get(i), true)); //Agregar a hashtable el nodo
			}else {
				conexiones.put(this.genOrder.get(i), new Nodo(this.genOrder.get(i), false)); //Agregar a hashtable el nodo
			}
		}
		
		
		for(int i = 0; i < this.genOrder.size(); i++) { //cada generador
			for (int j = 0; j < this.grammar.get(this.genOrder.get(i)).size(); j++) { //j para ir a cada elemento de la lista 
				String[] expre =  this.grammar.get(this.genOrder.get(i)).get(j).split(""); // hacer lista de cada elemento de una expresion generada
				System.out.println(Arrays.toString(expre));
				for (int k = 0; k < expre.length; k++) {
					if(this.generators.contains(expre[k])) {
						conexiones.get(this.genOrder.get(i)).addConexiones(conexiones.get(expre[k])); // se agrega el nodo al que etsa conectado en la hashtable
					}
				}
//				for (int k = 0; k < expre.length; k++) {
//					if(this.generators.contains(expre[k])) {
//						System.out.print(expre[k] + "-");
//						System.out.println(conexiones.get(expre[k]));
//						conexiones.get(this.genOrder.get(i)).addConexiones(conexiones.get(expre[k])); // se agrega el nodo al que etsa conectado en la hashtable
//					}
//				}
				
//				System.out.println(this.grammar.get(this.genOrder.get(i)).get(j));
//				System.out.println( this.genOrder.get(i) + " " + this.grammar.get(this.genOrder.get(i)).toString());
			}
			
		}
		

		
	}
	
	public static void main(String[] args) {
		String oa = "S→ASB A→aAS|a|ε B→SbS|A|bb";
		String gramatica = "S→(S)|SS|ß D→5";
		String pruebaInutiles = "S→aS|A|C A→a B→aa C→aCb";
		
		Analizador a = new Analizador(pruebaInutiles);
	}

}
