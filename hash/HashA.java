package hash;

import java.util.ArrayList;

import linkedList.ListLinked;
import linkedList.Node;

public class HashA<E extends Comparable<E>> {
    protected class Element implements Comparable<Element> {
        int key;
        E value;

        public Element(int key, E value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Element other) {
            return Integer.compare(this.key, other.key);
        }

        @Override
        public String toString() {
            return "{" + key + ", " + value + "}";
        }
    }

    protected ArrayList<ListLinked<Element>> table;
    protected int m;

    public HashA(int n) {
        this.m = n;
        this.table = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            this.table.add(new ListLinked<Element>());
        }
    }

    private int functionHash(int key) {
        return key % m;
    }

    private int functionHashSquare(int key) {
        int square = key * key;
        return (square / 100) % m;
    }

    private int functionHashFolding(int key) {
        int sum = 0;
        while (key > 0) {
            sum += key % 100;
            key /= 100;
        }
        return sum % m;
    }

    public void insert(int key, E value) {
        int index = functionHash(key); 
        table.get(index).insertLast(new Element(key, value));
    }

    public E search(int key) {
        int index = functionHash(key); 
        ListLinked<Element> chain = table.get(index);
        Node<Element> current = chain.head;
        while (current != null) {
            if (current.getData().key == key) {
                return current.getData().value;
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append("Index ").append(i).append(": ");
            Node<Element> current = table.get(i).head;
            while (current != null) {
                sb.append(current.getData()).append(" -> ");
                current = current.getNext();
            }
            sb.append("null\n");
        }
        return sb.toString();
    }
}
