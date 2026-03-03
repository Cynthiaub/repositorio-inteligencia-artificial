import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Puzzle25 {

	public static final String objetivo ="1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-20-21-22-23-24-_";

    public static boolean esObjetivo(String estado) {
    return estado.equals(objetivo);
}

    public static String[] generarSucesores(String estadoActual) {

    String[] piezas = estadoActual.split("-");

    if (piezas.length != 25) {
        throw new IllegalArgumentException(
            "El estado debe tener 25 posiciones separadas por guiones"
        );
    }

    int indiceVacio = -1;

    for (int i = 0; i < piezas.length; i++) {
        if (piezas[i].equals("_")) {
            indiceVacio = i;
            break;
        }
    }

    if (indiceVacio == -1) {
        throw new IllegalArgumentException(
            "El estado no contiene el espacio vacío (_)"
        );
    }
    int size = 5; 

    int fila = indiceVacio / size;
    int col = indiceVacio % size;

    List<String> sucesores = new ArrayList<>();

    int[][] movimientos = {
        {-1, 0}, // arriba
        {1, 0},  // abajo
        {0, -1}, // izquierda
        {0, 1}   // derecha
    };

    for (int[] mov : movimientos) {
        int nuevaFila = fila + mov[0];
        int nuevaCol = col + mov[1];

        if (nuevaFila >= 0 && nuevaFila < size &&
            nuevaCol >= 0 && nuevaCol < size) {

            int nuevoIndice = nuevaFila * size + nuevaCol;

            // crear copia
            String[] nuevoEstado = piezas.clone();

            // intercambiar
            nuevoEstado[indiceVacio] = nuevoEstado[nuevoIndice];
            nuevoEstado[nuevoIndice] = "_";

            // reconstruir estado con guiones
            sucesores.add(String.join("-", nuevoEstado));
        }
    }

    return sucesores.toArray(new String[0]);
}


public static int manhattan(String estado) {

    String[] piezas = estado.split("-");
    int size = (int) Math.sqrt(piezas.length);

    int distanciaTotal = 0;

    for (int i = 0; i < piezas.length; i++) {

        if (piezas[i].equals("_")) continue;

        int valor = Integer.parseInt(piezas[i]) - 1;

        int filaActual = i / size;
        int colActual = i % size;

        int filaObjetivo = valor / size;
        int colObjetivo = valor % size;

        distanciaTotal += Math.abs(filaActual - filaObjetivo)
                        + Math.abs(colActual - colObjetivo);
    }

    return distanciaTotal;
}


private static final int N = 5; // tamaño del tablero 5x5

public static String generarEstadoAleatorio() {

    List<String> piezas = new ArrayList<>();

    // agregar números 1 al 24
    for (int i = 1; i <= 24; i++) {
        piezas.add(String.valueOf(i));
    }

    // agregar espacio vacío
    piezas.add("_");

    Random random = new Random();

    String estado;

    do {
        Collections.shuffle(piezas, random);
        estado = String.join("-", piezas);
    }
    while (!esResoluble(estado));

    return estado;
}

private static boolean esResoluble(String estado) {

    String[] valores = estado.split("-");
    int inversiones = 0;
    int filaVacio = 0;
    int N = 5;

    // contar inversiones
    for (int i = 0; i < valores.length; i++) {

        if (valores[i].equals("_")) {
            filaVacio = i / N;
            continue;
        }

        for (int j = i + 1; j < valores.length; j++) {

            if (valores[j].equals("_")) continue;

            int a = Integer.parseInt(valores[i]);
            int b = Integer.parseInt(valores[j]);

            if (a > b) inversiones++;
        }
    }

    // Regla para tablero 5x5 (impar)
    // Si N es impar → resoluble si inversiones es PAR
    return inversiones % 2 == 0;
}

  public static int conflictoLineal(String estado) {

    String[] valores = estado.split("-");
    int conflictos = 0;

    // 🔹 Revisar filas
    for (int fila = 0; fila < N; fila++) {
        for (int col1 = 0; col1 < N; col1++) {

            int i = fila * N + col1;

            if (valores[i].equals("_")) continue;   // ⭐ ignorar vacío
            int v1 = Integer.parseInt(valores[i]);

            int metaFila1 = (v1 - 1) / N;
            int metaCol1 = (v1 - 1) % N;

            if (metaFila1 != fila) continue;

            for (int col2 = col1 + 1; col2 < N; col2++) {

                int j = fila * N + col2;

                if (valores[j].equals("_")) continue;   // ⭐ ignorar vacío
                int v2 = Integer.parseInt(valores[j]);

                int metaFila2 = (v2 - 1) / N;
                int metaCol2 = (v2 - 1) % N;

                if (metaFila2 == fila && metaCol1 > metaCol2) {
                    conflictos++;
                }
            }
        }
    }

    // 🔹 Revisar columnas
    for (int col = 0; col < N; col++) {
        for (int fila1 = 0; fila1 < N; fila1++) {

            int i = fila1 * N + col;

            if (valores[i].equals("_")) continue;   // ⭐ ignorar vacío
            int v1 = Integer.parseInt(valores[i]);

            int metaFila1 = (v1 - 1) / N;
            int metaCol1 = (v1 - 1) % N;

            if (metaCol1 != col) continue;

            for (int fila2 = fila1 + 1; fila2 < N; fila2++) {

                int j = fila2 * N + col;

                if (valores[j].equals("_")) continue;   // ⭐ ignorar vacío
                int v2 = Integer.parseInt(valores[j]);

                int metaFila2 = (v2 - 1) / N;
                int metaCol2 = (v2 - 1) % N;

                if (metaCol2 == col && metaFila1 > metaFila2) {
                    conflictos++;
                }
            }
        }
    }

    return conflictos * 2;
}
    public static int manhattanMasConflicto(String estado) {
        return manhattan(estado) + conflictoLineal(estado);
    }
}
