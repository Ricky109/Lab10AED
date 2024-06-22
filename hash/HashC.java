package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HashC<E extends Comparable <E>>{
    protected class Element{//Tienen una dependencia fuerte
        int mark; //0 = libre, 1 = ocupado, -1 = mark eliminacion
        Register <E> reg;

        public Element(int mark, Register<E> reg) {
            this.mark = mark;
            this.reg = reg;
        }
        
    }
    protected ArrayList<Element> table;
    protected int m; //tamaño de la tabla

    public HashC(int n) {
        this.m = n; //calcular el primo cercano a n y  asignarlo a m
        this.table = new ArrayList<Element>(m);
        for (int i = 0 ; i < m ; i++)
            this.table.add(new Element(0, null));
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
    
    private int functionHash (int key) {
        return key % this.m;
    }

    public void insert (int key, E value) {
        int dressHash = functionHash(key);
        dressHash = quadraticProbing(dressHash, key);
        if(dressHash == -1)
            System.out.println("Key duplicada ....");
        else if(dressHash == -2)
            System.out.println("Tabla Hash llena ....");
        else{
            Element ele = new Element(1, new Register<E>(key, value));
            this.table.set(dressHash,ele);
        }    
    }

    private int linearProbing (int dressHash, int key) {
        int start = dressHash;
        int temp = -1;
        do{
            if (this.table.get(dressHash).mark == 1 && this.table.get(dressHash).reg.key == key)
                return -1; //key duplicada
            else
                if (this.table.get(dressHash).mark == 1)
                    dressHash = (dressHash+1) % m;
                else {
                    if (this.table.get(dressHash).mark == -1){
                        temp= dressHash;
                        dressHash = (dressHash+1) % m;
                    }
                    if (this.table.get(dressHash).mark == 0)
                        return temp == -1 ? dressHash : temp;
                }  
        }
        while(dressHash != start);
        return -2; //tabla esta llena
    }
    
    private int quadraticProbing(int dressHash, int key) {
        int i = 0;
        int newHash;
        while (i < m) {
            newHash = (dressHash + i * i) % m;
            if (this.table.get(newHash).mark == 0 || this.table.get(newHash).mark == -1) {
                return newHash;
            } else if (this.table.get(newHash).mark == 1 && this.table.get(newHash).reg.key == key) {
                return -1; // clave duplicada
            }
            i++;
        }
        return -2; // tabla llena
    }
    
    public E search(int key) {
        int dressHash = functionHash(key);
        int start = dressHash;

        while (true) {
            Element element = this.table.get(dressHash);
            if (element.mark == 1 && element.reg.key == key) {
                return element.reg.value;
            } else if (element.mark == 0) {
                return null; //NO existe la clave en la tabla
            }
            dressHash = (dressHash + 1) % m;
            if (dressHash == start) {
                break;
            }
        }
        return null; //NO existe la clave en la tabla
    }
    
    public void remove(int key) {
        int dressHash = functionHash(key);
        int start = dressHash;
        do {
            if (this.table.get(dressHash).mark == 1 && this.table.get(dressHash).reg.key == key) {
                this.table.get(dressHash).mark = -1; // Marcar como eliminado
                return;
            } else {
                dressHash = (dressHash + 1) % m;
            }
        } while (dressHash != start && this.table.get(dressHash).mark != 0);
        System.out.println("Key no encontrada ....");
    }

    public String toString() {
        String str = "D. Real\tD. Hash\tRegister\n"; 
        int dirReal=0;
        for (Element item: this.table) {
            str+= (dirReal++) + " -->\t";
            if (item.mark == 1)
                str += functionHash(item.reg.key) + "\t" +item.reg+"\n";
            else
                str+="empty\n";
        }
        return str;
    }
    
    //Ejercicio 2
    public void loadFromFile(String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int n = Integer.parseInt(br.readLine());
            this.m = getNextPrime(n + (int) (0.4 * n)); // Calcular el tamaño de la tabla

            this.table = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                this.table.add(new Element(0, null));
            }

            String line;
            while ((line = br.readLine()) != null) {
            	String[] parts = line.split(" ", 4);
                //trim : para quitar espacios en blanco en los extremos del string
                int code = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String address = parts[2].trim();

                insert(code, (E) (name + ", " + address));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
