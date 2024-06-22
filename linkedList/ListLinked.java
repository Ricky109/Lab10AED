package linkedList;

public class ListLinked<T extends Comparable<T>> implements TDAList<T> {
    public Node<T> head;
    protected int size;

    public ListLinked() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public boolean isEmptyList() {
        return head == null;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public void destroyList() {
        head = null;
        size = 0;
    }

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

    @Override
    public void insertFirst(T x) {
        head = new Node<>(x, head);
        size++;
    }

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

    @Override
    public void removeNode(T x) {
        if (head == null) {
            return; // Lista vacía, no hay nada que eliminar
        }

        if (head.getData().equals(x)) {
            head = head.getNext();
            size--;
            return;
        }

        Node<T> current = head;
        Node<T> prev = null;

        while (current != null && !current.getData().equals(x)) {
            prev = current;
            current = current.getNext();
        }

        if (current != null) {
            prev.setNext(current.getNext());
            size--;
        }
    }

    // Método adicional para fusionar dos listas enlazadas ordenadas
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
        Node<T> dummy = new Node<T>(null);
        Node<T> current = dummy;

        int mergedSize = 0;

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

        head = dummy.getNext();
        size = mergedSize;
    }
}
