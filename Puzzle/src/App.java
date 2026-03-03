import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        String inicio =
      //"1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-_-17-18-19-20-16-21-22-23-24"; //ejemplo 1
      "1-2-3-4-5-7-11-_-8-10-16-6-13-9-14-21-12-23-19-15-17-22-18-20-24";  //ejemplo 2
       //"_-2-8-10-3-1-7-4-5-15-6-12-21-13-9-16-18-17-14-20-22-11-23-19-24"; //ejemplo 3
        String objetivo =
        "1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-20-21-22-23-24-_";

Scanner scanner = new Scanner(System.in);
    int opcion;

    do {

        Nodo raiz = new Nodo(inicio);
        ArbolBusqueda arbol = new ArbolBusqueda(raiz);
        Nodo solucion = null;

        mostrarMenu();
        opcion = scanner.nextInt();

        switch (opcion) {

            case 1:
                System.out.println("\nEjecutando Búsqueda en Anchura...");
                solucion = arbol.busquedaPrimeroAnchura(objetivo);
                break;

            case 2:
                System.out.println("\nEjecutando Búsqueda en Profundidad...");
                solucion = arbol.busquedaPorProfundidad(objetivo);
                break;

            case 3:
                System.out.println("\nEjecutando Búsqueda A*...");
                solucion = arbol.buscarSolucionEstrella(objetivo);
                break;
            case 4:
                System.out.println("Ejecutando IDA*...");
                solucion = arbol.buscarSolucionIDA(objetivo);
                break;

            case 0:
                System.out.println("\nSaliendo del programa...");
                break;

            default:
                System.out.println("\nOpción inválida. Intente nuevamente.");
                break;
        }

        if (solucion != null) {
            solucion.imprimir();
            if(opcion == 1) {
                System.out.println("\nSolución encontrada con Búsqueda en Anchura:");
            } else if (opcion == 2) {
                System.out.println("\nSolución encontrada con Búsqueda en Profundidad:");
            } else if (opcion == 3) {
                System.out.println("\nSolución encontrada con Búsqueda A*:");
            }
            System.out.println("\n========= RESULTADOS =========");

            System.out.printf("%-30s %d\n",
                    "Nodos expandidos:", arbol.getNodosExpandidos());

            System.out.printf("%-30s %d\n",
                    "Nodos generados:", arbol.getNodosGenerados());

            System.out.printf("%-30s %d ms\n",
                    "Tiempo de ejecución:", arbol.getTiempoTotal());

            System.out.printf("%-30s %d movimientos\n",
                    "Longitud de la solución:",
                    solucion.longitudSolucion());

            System.out.println("===============================");
        }

    } while (opcion != 0);

    scanner.close();
}


 private static void mostrarMenu() {
        System.out.println("\n==============================================");
        System.out.println("  ALGORITMOS DE BUSQUEDA (24-Puzzle)");
        System.out.println("==============================================");
        System.out.println("Seleccione el algoritmo a ejecutar:");
        System.out.println("1. Búsqueda en Anchura");
        System.out.println("2. Búsqueda en Profundidad");
        System.out.println("3. Búsqueda a Estrella (A*)");
        System.out.println("4. Búsqueda IDA*");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }
}