package hash;

public class TestArchivo {
    public static void main(String[] args) {
        HashC<String> hashTable = new HashC<>(10);
        hashTable.loadFromFile("D:/EMPLEADO.TXT");
        System.out.println(hashTable);
    }
}

