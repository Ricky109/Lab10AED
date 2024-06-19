package linkedList;

import java.util.LinkedList;

public class PruebaLinkedList {
    public static void main(String[] args) {
        // Crear una lista enlazada
        LinkedList<String> linkedList = new LinkedList<>();

        // Agregar elementos a la lista
        linkedList.add("Hola");
        linkedList.add("Mundo");
        linkedList.add("!");

        // Mostrar la lista
        System.out.println("Lista enlazada: " + linkedList);

        // Insertar un elemento en la segunda posición
        linkedList.add(1, "Java");

        // Mostrar la lista después de la inserción
        System.out.println("Lista enlazada después de la inserción: " + linkedList);

        // Eliminar el primer elemento
        linkedList.removeFirst();

        // Mostrar la lista después de la eliminación
        System.out.println("Lista enlazada después de eliminar el primer elemento: " + linkedList);
    }
}

