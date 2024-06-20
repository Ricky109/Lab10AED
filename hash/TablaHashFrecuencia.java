package hash;

import java.util.LinkedList;
import java.util.List;

class TablaHashFrecuencia {
    private class Pair {
        String key;
        int value;
        
        Pair(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<Pair>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public TablaHashFrecuencia(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % size;
    }

    public void insertar(String key) {
        int index = hash(key);
        List<Pair> bucket = table[index];
        for (Pair pair : bucket) {
            if (pair.key.equals(key)) {
                pair.value++;
                return;
            }
        }
        bucket.add(new Pair(key, 1));
    }

    public int frecuencia(String key) {
        int index = hash(key);
        List<Pair> bucket = table[index];
        for (Pair pair : bucket) {
            if (pair.key.equals(key)) {
                return pair.value;
            }
        }
        return 0;
    }
}
