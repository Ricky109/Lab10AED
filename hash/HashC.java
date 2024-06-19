package hash;

import java.util.ArrayList;

public class HashC<E extends Comparable<E>> {
	protected class Element{
		int mark;
		Register<E> reg;
		public Element(int mark, Register<E> reg) {
			this.mark=mark;
			this.reg=reg;
		}				
	}
	protected ArrayList<Element> table;
	protected int m;
	public HashC(int n) {
		this.m=n;
		this.table=new ArrayList<Element> (m);
		for(int i=0; i<m; i++) 
			this.table.add(new Element(0, null));
	}
	private int functionHash(int key) {
		return key%m;
	}
	private int linearProbing(int dressHash, int key) {
		int originalDressHash = dressHash;
        while (table.get(dressHash).mark == 1 && table.get(dressHash).reg.getKey() != key) {
            dressHash = (dressHash + 1) % m;
            if (dressHash == originalDressHash) {
                return -1; // Tabla llena
            }
        }
        return dressHash;
	}
	 public void insert(int key, E value) {
	        int dressHash = functionHash(key);
	        int pos = linearProbing(dressHash, key);
	        if (pos != -1) {
	            table.set(pos, new Element(1, new Register<>(key, value)));
	        } else {
	            System.out.println("Error: La tabla hash está llena");
	        }
	    }

	    public E search(int key) {
	        int dressHash = functionHash(key);
	        int pos = linearProbing(dressHash, key);
	        if (pos != -1 && table.get(pos).mark == 1) {
	            return table.get(pos).reg.value;
	        }
	        return null;
	    }

	    public String toString() {
	        String s = "D.Real\tD.Hash\tRegister\n";
	        int i = 0;
	        for (Element item : table) {
	            s += (i++) + "--->\t";
	            if (item.mark == 1) {
	                s += functionHash(item.reg.getKey()) + "\t" + item.reg + "\n";
	            } else {
	                s += "empty\n";
	            }
	        }
	        return s;
	    }
	
}
