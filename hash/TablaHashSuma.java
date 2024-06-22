package hash;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TablaHashSuma {
    private Map<Integer, List<Integer>> table;
    private int size;

    public TablaHashSuma(int size) {
        this.size = size;
        this.table = new HashMap<>();
    }

    private int hash(int key) {
        return key % size;
    }

    public void insertar(int key) {
        int index = hash(key);
        table.putIfAbsent(index, new ArrayList<>());
        table.get(index).add(key);
    }

    public boolean buscar(int key) {
        int index = hash(key);
        List<Integer> list = table.get(index);
        if (list != null) {
            for (int k : list) {
                if (k == key) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<int[]> encontrarPares(int[] lista, int suma) {
        TablaHashSuma tabla = new TablaHashSuma(10);
        List<int[]> pares = new ArrayList<>();

        for (int numero : lista) {
            int complemento = suma - numero;
            if (tabla.buscar(complemento)) {
                pares.add(new int[]{complemento, numero});
            }
            tabla.insertar(numero);
        }

        return pares;
    }

    public static void main(String[] args) {
        int[] lista = {1, 2, 3, 4, 5};
        int suma = 6;
        List<int[]> pares = encontrarPares(lista, suma);

        for (int[] par : pares) {
            System.out.println("(" + par[0] + ", " + par[1] + ")");
        }
    }
}
