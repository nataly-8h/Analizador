import java.util.ArrayList;

public class Nodo {
	String valor;
	private boolean Inicial;
	private ArrayList<Nodo> conexiones;
	
	public Nodo(String valor, boolean inicial, ArrayList<Nodo> conexiones) {
		this.valor = valor;
		Inicial = inicial;
		this.conexiones = conexiones;
	}
	

	public Nodo(String valor, boolean inicial) {
		super();
		this.valor = valor;
		Inicial = inicial;
		this.conexiones = new ArrayList<Nodo>();
	}


	@Override
	public String toString() {
		return this.valor + " " + this.Inicial + " " + this.conexiones.toString();
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
