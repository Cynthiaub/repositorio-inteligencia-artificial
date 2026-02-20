public class App {
    public static void main(String[] args){

        Nodo raiz = new Nodo("A");
        Nodo b = new Nodo("B");
        Nodo c = new Nodo("C");
        Nodo d = new Nodo("D");

        raiz.agregarHijo(b);
        raiz.agregarHijo(c);
        b.agregarHijo(d);

         Arbol arbol = new Arbol(raiz);

        Nodo resultado = arbol.buscarNodo("D");

        if(resultado != null){
            System.out.println("Nodo encontrado: " + resultado.getNodo());
        }else{
            System.out.println("No existe");
        }   
    }
}
