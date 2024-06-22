package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TablaHashFrecuencia {
    protected class Element {
        int mark; // 0 = libre, 1 = ocupado, -1 = marcado para eliminación
        String word;
        int frecuencia;

        public Element(int mark, String word) {
            this.mark = mark;
            this.word = word;
            this.frecuencia = 1;
        }
    }

    protected ArrayList<Element> table;
    protected int m; // tamaño de la tabla
    protected int numElements;
    
    public TablaHashFrecuencia(int n) {
        this.m = getNextPrime(n);
        this.table = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            this.table.add(new Element(0, null));
        }
    }

    public static int getNextPrime(int n) {
        while (true) {
            if (isPrime(n)) return n;
            n++;
        }
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private int functionHash(String word) {
        return Math.abs(word.hashCode() % this.m);
    }

    private int quadraticProbing(int hash, String word) {
        int i = 0;
        int newHash;
        while (i < m) {
            newHash = (hash + i * i) % m;
            if (this.table.get(newHash).mark == 0 || this.table.get(newHash).mark == -1) {
                return newHash;
            } else if (this.table.get(newHash).mark == 1 && this.table.get(newHash).word.equals(word)) {
                return -1; // palabra duplicada
            }
            i++;
        }
        return -2; // tabla llena
    }

    public void insert(String word) {
        int hash = functionHash(word);
        int index = quadraticProbing(hash, word);
        if (index == -1) {
            //ya esta la palabra hay que incrementar la frecuencia
            for (Element e : table) {
                if (e != null && e.word != null && e.word.equals(word)) {
                    e.frecuencia++;
                    return;
                }
            }
        } else if (index == -2) {
            //tabla llena se necesita rehashing
            System.out.println("Tabla Hash llena, rehashing...");
            rehash();
            insert(word);
            return;
        }
        Element ele = new Element(1, word);
        this.table.set(index, ele);
        numElements++;

        //rehashing si el factor de carga supera 0.7
        if ((float) numElements / m > 0.7) {
            rehash();
        }
    }

    private void rehash() {
        ArrayList<Element> tablaAnt = table;
        this.m = getNextPrime(m * 2);
        this.table = new ArrayList<>(m);
        this.numElements = 0;
        for (int i = 0; i < m; i++) {
            this.table.add(new Element(0, null));
        }
        for (Element e : tablaAnt) {
            if (e.mark == 1) {
                int hash = functionHash(e.word);
                int index = quadraticProbing(hash, e.word);
                Element newElement = new Element(1, e.word);
                newElement.frecuencia = e.frecuencia; // Transferir la frecuencia correcta
                this.table.set(index, newElement);
                this.numElements++;
            }
        }
    }

    public int getFrequency(String word) {
        int hash = functionHash(word);
        int index = quadraticProbing(hash, word);
        if (index == -1) {
            for (Element e : table) {
                if (e != null && e.word != null && e.word.equals(word)) {
                    return e.frecuencia;
                }
            }
        }
        return 0;
    }

    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\W+"); // separar por espacios en blanco
                for (String word : words) {
                    // Eliminar la puntuación y pasar la palabra a minúsculas
                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (!word.isEmpty()) {
                        insert(word);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Indice\tPalabra\tFrecuencia\n");
        for (int i = 0; i < table.size(); i++) {
            Element e = table.get(i);
            sb.append(i).append("\t");
            if (e.mark == 1) {
                sb.append(e.word).append("\t").append(e.frecuencia).append("\n");
            } else {
                sb.append("empty\n");
            }
        }
        return sb.toString();
    }
}

