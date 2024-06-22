package hash;

public class TestFrecuencia {

	public static void main(String[] args) {
        TablaHashFrecuencia hashTable = new TablaHashFrecuencia(10);
        hashTable.loadFromFile("D:/text.txt");
        System.out.println(hashTable);
    }

}
