import java.util.ArrayList;
import java.util.List;

public class Nodo {
    Nodo padre;
    String nodo;
    List<Nodo> hijos = new ArrayList<>();

    public Nodo(String nodo) {
        this.nodo = nodo;
    }

    public Nodo(Nodo padre, String nodo) {
        this.padre = padre;
        this.nodo = nodo;
    }

    public void agregarHijo(Nodo hijo){
        hijo.padre = this;
        hijos.add(hijo);
    }

    public List<Nodo> getHijos() {
        return hijos;
    }

    public String getNodo() {
        return nodo;
    }
}
