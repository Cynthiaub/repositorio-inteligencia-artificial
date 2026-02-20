public class Arbol {
    Nodo raiz;

    public Arbol(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public boolean vacio(){
        if(raiz==null){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Nodo buscarNodo(String nombre){
        return buscarRecursivo(raiz, nombre);
    }

    private Nodo buscarRecursivo(Nodo actual, String nombre){
        if(actual == null){
            return null;
        }

        if(actual.getNodo().equals(nombre)){
            return actual;
        }

        for(Nodo hijo : actual.getHijos()){
            Nodo encontrado = buscarRecursivo(hijo, nombre);
            if(encontrado != null){
                return encontrado;
            }
        }

        return null;
    }
}
