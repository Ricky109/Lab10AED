package linkedList;

public class ListLinked<T extends Comparable<T>> implements TDAList<T> {
    public Node<T> head;
    protected int size;

    public ListLinked() {
        this.head = null;
        this.size = 0;
    }

    // Verifica si la lista está vacía
    @Override
    public boolean isEmptyList() {
        return head == null;
    }

    // Retorna la longitud de la lista
    @Override
    public int length() {
        return size;
    }

    // Elimina todos los elementos de la lista
    @Override
    public void destroyList() {
        head = null;
        size = 0;
    }

    // Busca la posición de un elemento en la lista
    @Override
    public int search(T x) {
        Node<T> current = head;
        int index = 1;
        while (current != null) {
            if (current.getData().equals(x)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1; // Elemento no encontrado
    }

    // Inserta un elemento al principio de la lista
    @Override
    public void insertFirst(T x) {
        head = new Node<>(x, head);
        size++;
    }

    // Inserta un elemento al final de la lista
    @Override
    public void insertLast(T x) {
        if (head == null) {
            insertFirst(x);
            return;
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        Node<T> nod = new Node<>(x, null);
        current.setNext(nod);
        size++;
    }

    // Elimina un nodo que contiene el elemento dado
    @Override
    public void removeNode(T x) {
        if (head == null) {
            return; // Lista vacía, no hay nada que eliminar
        }

        // Eliminar el primer nodo si contiene el elemento dado
        if (head.getData().equals(x)) {
            head = head.getNext();
            size--;
            return;
        }

        Node<T> current = head;
        Node<T> prev = null;

        // Buscar el nodo que contiene el elemento dado
        while (current != null && !current.getData().equals(x)) {
            prev = current;
            current = current.getNext();
        }

        // Si el nodo fue encontrado, eliminarlo
        if (current != null) {
            prev.setNext(current.getNext());
            size--;
        }
    }

    public void mergeTwoLists(ListLinked<T> list1, ListLinked<T> list2) {
        if (list1.isEmptyList()) {
            head = list2.head;
            size = list2.size;
            return;
        } else if (list2.isEmptyList()) {
            head = list1.head;
            size = list1.size;
            return;
        }

        Node<T> current1 = list1.head;
        Node<T> current2 = list2.head;
        Node<T> dummy = new Node<T>(null); // Nodo dummy para facilitar la fusión
        Node<T> current = dummy;

        int mergedSize = 0; // Tamaño de la lista fusionada

        // Fusionar las dos listas
        while (current1 != null && current2 != null) {
            if (current1.getData().compareTo(current2.getData()) < 0) {
                current.setNext(current1);
                current1 = current1.getNext();
            } else {
                current.setNext(current2);
                current2 = current2.getNext();
            }
            current = current.getNext();
            mergedSize++;
        }

        // Agregar los nodos restantes de list1 o list2
        if (current1 != null) {
            current.setNext(current1);
            while (current1 != null) {
                current1 = current1.getNext();
                mergedSize++;
            }
        } else {
            current.setNext(current2);
            while (current2 != null) {
                current2 = current2.getNext();
                mergedSize++;
            }
        }

        // Establecer la cabeza de la lista fusionada y actualizar el tamaño
        head = dummy.getNext();
        size = mergedSize;
    }
}

