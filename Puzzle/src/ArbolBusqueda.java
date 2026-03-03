
import java.util.*;

public class ArbolBusqueda {
    private long inicioTiempo = 0;
    private long finTiempo = 0;
    private long tiempoTotal = 0;
    private int nodosGenerados = 0;
	private int nodosExpandidos = 0;
	private Nodo solucionEncontrada = null;
	private Nodo raiz;

    public long getTiempoTotal() {
        return tiempoTotal;
    }
    
    public int getNodosGenerados() {
        return nodosGenerados;
    }
	public ArbolBusqueda(Nodo raiz) {
		this.raiz = raiz;
	}
    public int getNodosExpandidos() {
    return nodosExpandidos;
    }
    public void reiniciarMetricas() {
        nodosExpandidos = 0;
        nodosGenerados = 0;
    }	
	public Nodo busquedaPrimeroAnchura(String estadoObjetivo) {

    reiniciarMetricas();
        inicioTiempo = System.currentTimeMillis();
    if (raiz == null) return null;

    HashSet<String> visitados = new HashSet<>();
    Queue<Nodo> cola = new LinkedList<>();

    cola.add(raiz);
    visitados.add(raiz.estado);

    while (!cola.isEmpty()) {

        Nodo actual = cola.poll();
        nodosExpandidos++;   

        if (actual.estado.equals(estadoObjetivo)) {
            finTiempo = System.currentTimeMillis();  
            tiempoTotal = finTiempo - inicioTiempo;
            return actual;
        }

        for (Nodo sucesor : actual.generarSucesores()) {
            nodosGenerados++;
            if (!visitados.contains(sucesor.estado)) {
                cola.add(sucesor);
                visitados.add(sucesor.estado);
            }
        }
    }
    return null;
}

	public Nodo busquedaPorProfundidad(String estadoObjetivo) {

    reiniciarMetricas();
            inicioTiempo = System.currentTimeMillis();
    if (raiz == null) return null;

    HashSet<String> visitados = new HashSet<>();
    Stack<Nodo> pila = new Stack<>();

    pila.push(raiz);

    while (!pila.isEmpty()) {

        Nodo actual = pila.pop();
        nodosExpandidos++;   

        if (actual.estado.equals(estadoObjetivo)) {
            finTiempo = System.currentTimeMillis();
            tiempoTotal = finTiempo - inicioTiempo;
            return actual;
        }

        if (visitados.contains(actual.estado)) continue;

        visitados.add(actual.estado);

        for (Nodo sucesor : actual.generarSucesores()) {
            nodosGenerados++;
            if (!visitados.contains(sucesor.estado)) {
                pila.push(sucesor);
            }
        }
    }
    return null;
}

public Nodo buscarSolucionEstrella(String objetivo) {

    reiniciarMetricas();
    inicioTiempo = System.currentTimeMillis();
        PriorityQueue<Nodo> abiertos = new PriorityQueue<>(
                Comparator.comparingInt(Nodo::getF)
        );
        HashSet<String> cerrados = new HashSet<>();
        abiertos.add(raiz);
        
        while (!abiertos.isEmpty()) {

            Nodo actual = abiertos.poll();
            nodosExpandidos++; 
            if (actual.getEstado().equals(objetivo)) {
                finTiempo = System.currentTimeMillis();
                tiempoTotal = finTiempo - inicioTiempo;
                return actual;
            }

            cerrados.add(actual.getEstado());

            for (Nodo sucesor : actual.generarSucesores()) {
                 nodosGenerados++;
                if (!cerrados.contains(sucesor.getEstado())) {
                    abiertos.add(sucesor);
                }
            }
        }

        return null; 
    }

    public Nodo buscarSolucionIDA(String objetivo) {

    reiniciarMetricas();
    inicioTiempo = System.currentTimeMillis();

    int limite = raiz.getF(); // límite inicial

    while (true) {

        HashSet<String> visitados = new HashSet<>();
        ResultadoIDA resultado = dfsLimitado(raiz, objetivo, limite, visitados);

        if (resultado.encontrado != null) {
            finTiempo = System.currentTimeMillis();
            tiempoTotal = finTiempo - inicioTiempo;
            return resultado.encontrado;
        }

        if (resultado.nuevoLimite == Integer.MAX_VALUE) {
            return null; 
        }

        limite = resultado.nuevoLimite; 
    }
}

private ResultadoIDA dfsLimitado(Nodo actual,String objetivo, int limite, HashSet<String> visitados) {

    int f = actual.getF();

    if (f > limite) {
        return new ResultadoIDA(null, f);
    }

    nodosExpandidos++;

    if (actual.getEstado().equals(objetivo)) {
        return new ResultadoIDA(actual, limite);
    }

    visitados.add(actual.getEstado());

    int minimo = Integer.MAX_VALUE;

    for (Nodo sucesor : actual.generarSucesores()) {

        nodosGenerados++;

        if (!visitados.contains(sucesor.getEstado())) {

            ResultadoIDA resultado =
                    dfsLimitado(sucesor, objetivo, limite, visitados);

            if (resultado.encontrado != null) {
                return resultado;
            }

            minimo = Math.min(minimo, resultado.nuevoLimite);
        }
    }

    visitados.remove(actual.getEstado());

    return new ResultadoIDA(null, minimo);
}

private class ResultadoIDA {
    Nodo encontrado;
    int nuevoLimite;

    ResultadoIDA(Nodo encontrado, int nuevoLimite) {
        this.encontrado = encontrado;
        this.nuevoLimite = nuevoLimite;
    }
}
}

