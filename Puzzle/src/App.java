public class App {
    public static void main(String[] args) {
        Nodo nodo = new Nodo("1238 4765");
        
        ArbolBusqueda puzzle = new ArbolBusqueda(nodo);
        Nodo n = puzzle.busquedaPorProfundidad("12 843765");

        if(n != null)
            n.impirmir();
        else
            System.out.println("No se encontró solución");
    }
}
