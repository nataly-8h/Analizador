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
		int j = 1;
		int bi = 1; //buscar en i
		int bj = 0; //buscar en j
		int startj = 1;
		int maxi = this.tableSize -startj -1;
		
		while(!this.table[this.tableSize -1][this.tableSize -1].equals("-")) {
			ArrayList<String> find = new ArrayList<String>();
			String found = "";
			
			while(bj<j) {
				String searchi = this.table[i][bj];
				//System.out.println("bi " + bi + " bj " + bj);
				String searchj = this.table[bi][j];
				
				if(!find.contains(findGenerators(searchi + searchj))) {
					//System.out.println(findGenerators(searchi + searchj));

					find.add(findGenerators(searchi + searchj));
				}
				bj ++;
				bi ++;	
			}
			
			for (int k = 0; k < find.size(); k++) {
				if(find.get(k).equals("Ø")) {
					if(find.size() == 1) {
						found = found + find.get(k);
					}
				}else {
					found = found + find.get(k);
				}
				
			}
			
			this.table[i][j] = found;
			found = "";
			find.clear();
			
			System.out.println("i " + i + " maxi " + maxi);
			if(i == maxi) {
				System.out.println("equina");
				startj++;
				maxi --;
				i = 0;
				j = startj;
				bi = 1; 
				bj = 0; 
				
			}else {
				i++;
				j++;				
			}
				
			
			System.out.println();
			System.out.println(i == maxi);
			for (int k = 0; k < table.length; k++) {
				System.out.println(Arrays.toString(this.table[k]));
			}
			

		}
		
		//imprimir tabla
		for (int k = 0; k < table.length; k++) {
			System.out.println(Arrays.toString(this.table[k]));
		}
		//System.out.println(findGenerators("AA"));
		
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
