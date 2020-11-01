import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class CYK {
	public Analizador analizar;
	public ArrayList<String> genOrder;
	
	public Hashtable<String, ArrayList<String>> CYKgrammar;
	public ArrayList<String> gramOrd;
	public String cadena;
	public int tableSize;
	public String[][] table; //tabla donde se agregan los numeros
	
	
	public CYK(Analizador a, String cadena) {
		this.analizar = a;
		this.genOrder = a.genOrder;
		
		CYKgrammar = a.grammar;
		System.out.println("CYK " + this.CYKgrammar.toString());
		this.cadena = cadena;
		this.tableSize = cadena.length();
		System.out.println(tableSize);
		this.table = new String[this.tableSize][this.tableSize];
		
//		for (int i = 0; i < table.length; i++) {
//			System.out.println(Arrays.toString(this.table[i]));
//		}
		//System.out.println(this.table[0][0] == null);
		
		for (int i = 0; i < table.length; i++) {
			this.table[0][i] = "-";
		}

		int j = 0;
		for (int i = 1; i < table.length; i++) {
			while(j < this.tableSize) {
				this.table[i ][j] = "-";
				j++;
			}
			j = 0;
			while(j <= i) {
				this.table[i][j] = " ";
				j++;
			}
			j = 0;
		}
		
		analizer();
	}
	
	public void analizer() {
		for (int m = 0; m < table.length; m++) { //poner orillas del arbol
			this.table[m][m] = findGenerators(Character.toString(this.cadena.charAt(m)));		
		}
		
		//cyk
		int i = 0;
		int j = 0;
		while(this.table[this.tableSize -1][this.tableSize -1] == null) {
			this.table[i][j] = "A";
			i ++;
			j++;
		}
		
		//imprimir tabla
		for (int k = 0; k < table.length; k++) {
			System.out.println(Arrays.toString(this.table[k]));
		}
		System.out.println(findGenerators("AA"));
		
	}
	
	public String findGenerators(String expr) {
		String symbol = "";
		for(int i = 0; i < this.genOrder.size(); i++) { //cada generador
			if(this.CYKgrammar.get(this.genOrder.get(i)).contains(expr)) {
				symbol = symbol + this.genOrder.get(i);
			}
		  }
		if(symbol.isEmpty()) {
			symbol = "Ø";
		}
		return symbol;
	}


	public static void main(String[] args) {
		Analizador a = new Analizador("S→AB|SS|AC|BD|BA A→a B→b C→SB D→SA");
		System.out.println(a.grammar.toString());
		String pruebaCadena =  "aabbab";
		CYK b = new CYK(a, pruebaCadena);
		
	}

}
