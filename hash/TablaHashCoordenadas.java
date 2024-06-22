package hash;

import java.util.ArrayList;
import linkedList.ListLinked;
import linkedList.Node;

public class TablaHashCoordenadas {

    private ListLinked<Elemento>[] tabla;
    private int tamaño;

    private static class Elemento {
        int x;
        int y;
        String valor;

        public Elemento(int x, int y, String valor) {
            this.x = x;
            this.y = y;
            this.valor = valor;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String getValor() {
            return valor;
        }
    }

    public TablaHashCoordenadas(int tamaño) {
        this.tamaño = tamaño;
        tabla = new ListLinked[tamaño];
        for (int i = 0; i < tamaño; i++) {
            tabla[i] = new ListLinked<>();
        }
    }

    private int hash(int x, int y) {
        return Math.abs((x * 31 + y) % tamaño);
    }

    public void insertar(int x, int y, String valor) {
        int indice = hash(x, y);
        ListLinked<Elemento> lista = tabla[indice];
        lista.insertLast(new Elemento(x, y, valor));
    }

    public String buscar(int x, int y) {
        int indice = hash(x, y);
        ListLinked<Elemento> lista = tabla[indice];
        Node<Elemento> current = lista.head;
        while (current != null) {
            if (current.getData().getX() == x && current.getData().getY() == y) {
                return current.getData().getValor();
            }
            current = current.getNext();
        }
        return null;
    }

    public String eliminar(int x, int y) {
        int indice = hash(x, y);
        ListLinked<Elemento> lista = tabla[indice];
        Node<Elemento> current = lista.head;
        Node<Elemento> prev = null;

        while (current != null) {
            if (current.getData().getX() == x && current.getData().getY() == y) {
                if (prev == null) {
                    lista.head = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                return current.getData().getValor();
            }
            prev = current;
            current = current.getNext();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tabla Hash de Coordenadas:\n");
        for (int i = 0; i < tamaño; i++) {
            sb.append("Índice ").append(i).append(": ");
            ListLinked<Elemento> lista = tabla[i];
            Node<Elemento> current = lista.head;
            while (current != null) {
                sb.append("(").append(current.getData().getX()).append(",")
                  .append(current.getData().getY()).append(",")
                  .append(current.getData().getValor()).append(") ");
                current = current.getNext();
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TablaHashCoordenadas tabla = new TablaHashCoordenadas(10);
        tabla.insertar(1, 2, "valor1");
        tabla.insertar(3, 4, "valor2");
    
        System.out.println(tabla.buscar(1, 2)); // Output: valor1
        System.out.println(tabla.buscar(3, 4)); // Output: valor2
    
        System.out.println(tabla.eliminar(1, 2)); // Output: valor1
        System.out.println(tabla.buscar(1, 2)); // Output: null
    
        System.out.println(tabla); // Imprimir la tabla completa
    }
}