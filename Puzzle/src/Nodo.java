import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Nodo implements Comparable<Nodo> {
	String estado;
	private Nodo padre;
	private int nivel, costo;
	public Nodo(String estado) {
		this.estado = estado;
	}
	public Nodo(String estado, Nodo padre) {
		this.estado = estado;
		this.padre = padre;
	}
	public Nodo(String estado, Nodo padre, int nivel) {
		this.estado = estado;
		this.padre = padre;
		this.nivel = nivel;
	}
	public String getEstado() {
		return estado;
	}
	public Nodo getPadre() {
		return padre;
	}
	public int getNivel() {
		return nivel;
	}
	public int getCosto() {
		return costo;
	}
	

	public List<Nodo> generarSucesores() {
		//String[] estadoHijos = Puzzle8.generarSucesores(estado);
		String[] estadoHijos = Puzzle25.generarSucesores(estado);
		List<Nodo> sucesores = new ArrayList<Nodo>();
		for(String estadoHijo: estadoHijos) {
			if(estadoHijo != null) {
				Nodo nodoHijo = new Nodo(estadoHijo, this, this.nivel+1);
				sucesores.add(nodoHijo);
			}
		}
		return sucesores;
	}

	public void imprimir() {
    if (padre != null) {
        padre.imprimir();
    }

    String[] piezas = estado.split("-");
    int size = (int) Math.sqrt(piezas.length); 

    System.out.println("Nivel: " + nivel);
    System.out.println("-------------------------");

    for (int i = 0; i < piezas.length; i++) {
        System.out.printf("%4s", piezas[i]); 
        if ((i + 1) % size == 0) {
            System.out.println();
        }
    }

    System.out.println("-------------------------");
	System.out.println("Costo (g): " + nivel);
	System.out.println("Heurística (h): " + getHeuristica());
	System.out.println("Función f(n) = g(n) + h(n): " + getF());

    System.out.println("-------------------------\n");
}
        

public int getHeuristica() {
    //return Puzzle25.manhattan(estado);
	return Puzzle25.manhattanMasConflicto(this.estado);
}

public int getF() {
    return nivel + getHeuristica();
}

public int longitudSolucion() {
    int movimientos = 0;
    Nodo actual = this;

    while (actual.padre != null) {
        movimientos++;
        actual = actual.padre;
    }

    return movimientos;
}

@Override
public int compareTo(Nodo otro) {
    return Integer.compare(this.getF(), otro.getF());
}

}
