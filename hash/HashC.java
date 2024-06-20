package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HashC<E extends Comparable<E>> {
    protected class Element {
        int mark; 
        Register<E> reg;
        int searchLength;

        public Element(int mark, Register<E> reg, int searchLength) {
            this.mark = mark;
            this.reg = reg;
            this.searchLength = searchLength;
        }
    }
    
    protected ArrayList<Element> table;
    protected int m;

    public HashC(int n) {
        this.m = n;
        this.table = new ArrayList<Element>(m);
        for (int i = 0; i < m; i++) 
            this.table.add(new Element(0, null, 0));
    }

	public HashC(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int n = Integer.parseInt(br.readLine().trim());
            this.m = nextPrime((int) Math.ceil(n * 1.4));
            this.table = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                this.table.add(new Element(0, null, 0));
            }
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ", 4);
                int code = Integer.parseInt(parts[0]);
                String name = parts[1] + " " + parts[2] + " " + parts[3];
                insert(code, (E) name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int functionHash(int key) {
        return key % m;
    }

    private int quadraticProbing(int dressHash, int key) {
        int originalDressHash = dressHash;
        int i = 1;
        while (table.get(dressHash).mark == 1 && table.get(dressHash).reg.getKey() != key) {
            dressHash = (originalDressHash + i * i) % m;
            i++;
            if (i > m) {
                return -1; 
            }
        }
        return dressHash;
    }

	private int quadraticProbing(int dressHash, int key, int[] searchLength) {
        int originalDressHash = dressHash;
        int i = 0;
        searchLength[0] = 1;
        while (table.get(dressHash).mark == 1 && table.get(dressHash).reg.getKey() != key) {
            i++;
            dressHash = (originalDressHash + i * i) % m;
            searchLength[0]++;
            if (searchLength[0] > m) {
                return -1; // Tabla llena
            }
        }
        return dressHash;
    }

   	public void insert(int key, E value) {
        int dressHash = functionHash(key);
        int[] searchLength = {1};
        int pos = quadraticProbing(dressHash, key, searchLength);
        if (pos != -1) {
            table.set(pos, new Element(1, new Register<>(key, value), searchLength[0]));
        } else {
            System.out.println("Error: La tabla hash está llena");
        }
    }

    public E search(int key) {
        int dressHash = functionHash(key);
        int pos = quadraticProbing(dressHash, key);
        if (pos != -1 && table.get(pos).mark == 1) {
            return table.get(pos).reg.value;
        }
        return null;
    }

    public String toString() {
        String s = "D.Real\tD.Hash\tRegister\tSearch Length\n";
        int i = 0;
        for (Element item : table) {
            s += (i++) + "--->\t";
            if (item.mark == 1) {
                s += functionHash(item.reg.getKey()) + "\t" + item.reg + "\t" + item.searchLength + "\n";
            } else {
                s += "empty\n";
            }
        }
        return s;
    }

	private static int nextPrime(int num) {
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }

	private static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }
}
