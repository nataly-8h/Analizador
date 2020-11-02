import java.util.ArrayList;

public class Nodo {
	String valor;
	private boolean Inicial;
	private ArrayList<Nodo> conexiones;
	
	public Nodo(String valor, boolean inicial, ArrayList<Nodo> conexiones) {
		this.valor = valor;
		Inicial = inicial;
		this.conexiones = new ArrayList<Nodo>();
		for (int i = 0; i < conexiones.size(); i++) {
			if(!this.conexiones.contains(conexiones.get(i))) {
				this.conexiones.add(conexiones.get(i));
			}
		}
	}
	

	public Nodo(String valor, boolean inicial) {
		super();
		this.valor = valor;
		Inicial = inicial;
		this.conexiones = new ArrayList<Nodo>();
	}


	public String nToString() {
		String nStrng = this.valor + " " + this.Inicial + "{";
		for (int i = 0; i < this.conexiones.size(); i++) {
			nStrng = nStrng + " " + this.conexiones.get(i).getValor() + ","; 
		}
		nStrng = nStrng + " }";
		return nStrng;
	}


	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean isInicial() {
		return Inicial;
	}

	public void setInicial(boolean inicial) {
		Inicial = inicial;
	}

	public ArrayList<Nodo> getConexiones() {
		return conexiones;
	}
	
	public ArrayList<String> getConexionesString() {
		ArrayList<String> conexionesS = new ArrayList<String>();
		for (int i = 0; i < this.conexiones.size(); i++) {
			conexionesS.add(this.conexiones.get(i).getValor());
		}
		return conexionesS;
	}
	
	public void addConexiones(Nodo node) {
		this.conexiones.add(node);
	}

	public void setConexiones(ArrayList<Nodo> conexiones) {
		this.conexiones = conexiones;
	}
	
	public Nodo() {
		// TODO Auto-generated constructor stub
	
	}

}
