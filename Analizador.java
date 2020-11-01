import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;

public class Analizador {
  public String[] gramatica;
  public ArrayList<String> terminals,
               genOrder;
  //public Hashtable<String, String> generators;
  
  public Hashtable<String, ArrayList<String>> grammar;
  Hashtable<String, Nodo> conexiones;

  public Analizador(String gramatic) {
    int generadores = 0;
    this.gramatica = gramatic.split("");
    //this.generators = new Hashtable<String, String>();
    this.terminals = new ArrayList<String>();
    this.genOrder = new ArrayList<String>();
    
    //obtencion de generadores de la gramatica
    for(int i = 0; i < gramatica.length -1; i++) {
      if(gramatica[i+1].equals("→")) {
        //this.generators.put(gramatica[i], gramatica[i]);
        this.genOrder.add( gramatica[i]);
      }
    }
    
    //obtencion de elementos terminales 
    for (int i = 0; i < gramatica.length; i++) {
      //en este if tambien se eliminan los Generadores que no generan nada nada
      if(!gramatica[i].equals("→") && !this.genOrder.contains(gramatica[i]) && !gramatica[i].equals("|") && !gramatica[i].isBlank() && !terminals.contains(gramatica[i]) && !Character.isUpperCase(gramatica[i].charAt(0))) {
        this.terminals.add(gramatica[i]);
      }
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
    
	//Pasos para chomsky:
    inutiles();
    conexiones();
    
  }
  
  public void inutiles() {
    ArrayList<String> N1 = new ArrayList<String>(); // N1 = ÃƒÆ’Ã‚Â¸
    ArrayList<String> Ns = new ArrayList<String>(); // ÃƒÂ¢Ã‹â€ Ã¢â‚¬Ëœ
    
    for (int i = 0; i < this.terminals.size(); i++) { // agregar los valores de ÃƒÂ¢Ã‹â€ Ã¢â‚¬Ëœ
      Ns.add(this.terminals.get(i));
    }
    int prevSize = 0;
    int currentSize = Ns.size();
    
    while(currentSize != prevSize) {
      for(int i = 0; i < this.genOrder.size(); i++) { //cada generador
        if(!Ns.contains(this.genOrder.get(i))) {
          for (int j = 0; j < this.grammar.get(this.genOrder.get(i)).size(); j++) { //j para ir a cada elemento de la lista 
            String[] expre =  this.grammar.get(this.genOrder.get(i)).get(j).split(""); // hacer lista de cada elemento de una expresion generada
            String finexpresion = "";
            for (int k = 0; k < expre.length; k++) {
              if(Ns.contains(expre[k])) {
                finexpresion = finexpresion + expre[k];
                if(finexpresion.equals(this.grammar.get(this.genOrder.get(i)).get(j))) { //Si al terminar la expresion es igual a toda la expresion, agregar el generador
                  if(!N1.contains(this.genOrder.get(i)) && !Ns.contains(this.genOrder.get(i))) {
                    N1.add(this.genOrder.get(i));
                  }
                }else {
                  if(k == expre.length -1) {
                    finexpresion = "";
                  }
                }
              }else {
              }
            }
          }
        }else {
          
        }
      }
//    System.out.println("N1: " + N1.toString());
//    System.out.println("Ns: " + Ns.toString());
    
    prevSize = Ns.size();
    for (int i = 0; i < N1.size(); i++) {
      if(!Ns.contains(N1.get(i))) {
        Ns.add(N1.get(i));
      }  
    }  
    currentSize = Ns.size();
//    System.out.println("Curr" + currentSize + " Prev" + prevSize);
  }
  
  //Remover los Generadores inutiles de la gramatica 
  ArrayList<String> eliminados = new ArrayList<String>();
 // System.out.println(this.grammar.toString());
  for (int i = 0; i < this.genOrder.size(); i++) {
    if(!Ns.contains(this.genOrder.get(i))) {
      this.grammar.remove(this.genOrder.get(i));
      eliminados.add(this.genOrder.get(i));
      this.genOrder.remove(i);
    }
  }
  //System.out.println(this.grammar.toString());
  
  //Remover las expresiones que contienen el generador inutil
  for(int i = 0; i < this.genOrder.size(); i++) { //cada generador
    for (int j = 0; j < this.grammar.get(this.genOrder.get(i)).size(); j++) { //j para ir a cada elemento de la lista 
      String[] expre =  this.grammar.get(this.genOrder.get(i)).get(j).split(""); // hacer lista de cada elemento de una expresion generada
      String finexpresion = "";
      for (int k = 0; k < expre.length; k++) {
        if(eliminados.contains(expre[k])) {
          this.grammar.get(this.genOrder.get(i)).remove(j);
        }
      }
    }
  }
  
  System.out.println(this.grammar.toString());
  
  
}
  
  public void conexiones() {
	  System.out.println();
	  //conexiones
	  this.conexiones = new Hashtable<String, Nodo>();
	  //Nodo[] this.conexiones = new Nodo[this.genOrder.size()];

	  for(int i = 0; i<this.genOrder.size(); i++) {
		  if(i==0) {
			  this.conexiones.put(this.genOrder.get(i), new Nodo(this.genOrder.get(i), true)); //Agregar a hashtable el nodo
		  }else {
			  this.conexiones.put(this.genOrder.get(i), new Nodo(this.genOrder.get(i), false)); //Agregar a hashtable el nodo
		  }
	  }


	  for(int i = 0; i < this.genOrder.size(); i++) { //cada generador
		  for (int j = 0; j < this.grammar.get(this.genOrder.get(i)).size(); j++) { //j para ir a cada elemento de la lista 
			  String[] expre =  this.grammar.get(this.genOrder.get(i)).get(j).split(""); // hacer lista de cada elemento de una expresion generada
			  for (int k = 0; k < expre.length; k++) {
				  if(this.genOrder.contains(expre[k])) {
					  this.conexiones.get(this.genOrder.get(i)).addConexiones(this.conexiones.get(expre[k])); // se agrega el nodo al que etsa conectado en la hashtable
				  }
			  }
		  }

	  }

	  //imprimir las conexiones del nodo
//	  for (int i = 0; i < this.genOrder.size(); i++) {
//		  System.out.println(this.conexiones.get(this.genOrder.get(i)).nToString());
//	  }
//	  
//	  //eliminar conexiones a las que no se puede llegar desde el nodo inicial
//	  ArrayList<Nodo> caminoS = new ArrayList<Nodo>();
//	  for (int i = 0; i < this.conexiones.get(0).getConexiones().size(); i++) {
//	  }

  }
  public void caminoS(ArrayList<Nodo> conect) {
	  
  }

public static void main(String[] args) {
	String oa = "S→ASB A→aAS|a|ß B→SbS|A|bb";
    String gramatica = "S→(S)|SS|ß D→5";
    String pruebaInutiles = "S→aS|A|C A→a B→aa C→aCb";
    String prueba = "S→aA|B|D A→aA|bA|B B→b|ß C→abd";
  Analizador a = new Analizador(pruebaInutiles);
}

}