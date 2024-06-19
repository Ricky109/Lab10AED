package hash;

class TestHash {
    public static void main(String[] args) {
        String fileName = "D:/EMPLEADO.TXT";
        HashC<String> hashTable = new HashC<>(fileName);
        System.out.println(hashTable);
    }
}