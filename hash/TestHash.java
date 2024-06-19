package hash;

public class TestHash {
    public static void main(String[] args) {
        // Probando HashC
        System.out.println("Probando HashC:");
        HashC<String> hashTableC = new HashC<>(11);

        int[] keysC = {34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34};
        String[] namesC = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Ivy", "Jack", "Karen"};

        for (int i = 0; i < keysC.length; i++) {
            hashTableC.insert(keysC[i], namesC[i]);
        }

        System.out.println(hashTableC);

        // Probando HashA
        System.out.println("\nProbando HashA:");
        HashA<String> hashTableA = new HashA<>(7);

        int[] keysA = {34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34};
        String[] namesA = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Ivy", "Jack", "Karen"};

        for (int i = 0; i < keysA.length; i++) {
            hashTableA.insert(keysA[i], namesA[i]);
        }

        System.out.println(hashTableA.toString());
    }
}
