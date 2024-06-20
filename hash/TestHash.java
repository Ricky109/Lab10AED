package hash;

public class TestHash {
    public static void main(String[] args) {
    	// Probando HashC con método del cuadrado
        System.out.println("Probando HashC con método del cuadrado:");
        HashC<String> hashTableCSquare = new HashC<>(11);
        int[] keysC = {34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34};
        String[] namesC = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Ivy", "Jack", "Karen"};

        for (int i = 0; i < keysC.length; i++) {
            hashTableCSquare.insert(keysC[i], namesC[i]);
        }
        System.out.println(hashTableCSquare);

        // Probando HashC con método por pliegue aplicando suma
        System.out.println("\nProbando HashC con método por pliegue aplicando suma:");
        HashC<String> hashTableCFolding = new HashC<>(11);

        for (int i = 0; i < keysC.length; i++) {
            hashTableCFolding.insert(keysC[i], namesC[i]);
        }
        System.out.println(hashTableCFolding);

        // Probando HashA con método del cuadrado
        System.out.println("\nProbando HashA con método del cuadrado:");
        HashA<String> hashTableASquare = new HashA<>(7);

        int[] keysA = {34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34};
        String[] namesA = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Ivy", "Jack", "Karen"};

        for (int i = 0; i < keysA.length; i++) {
            hashTableASquare.insert(keysA[i], namesA[i]);
        }
        System.out.println(hashTableASquare);

        // Probando HashA con método por pliegue aplicando suma
        System.out.println("\nProbando HashA con método por pliegue aplicando suma:");
        HashA<String> hashTableAFolding = new HashA<>(7);

        for (int i = 0; i < keysA.length; i++) {
            hashTableAFolding.insert(keysA[i], namesA[i]);
        }
        System.out.println(hashTableAFolding);
    }
}
