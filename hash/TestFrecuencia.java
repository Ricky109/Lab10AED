package hash;

public class TestFrecuencia {

	public static void main(String[] args) {
        String texto = "hola mundo hola adios mundo mundo";
        String[] palabras = texto.split(" ");
        TablaHashFrecuencia tabla = new TablaHashFrecuencia(10);
        for (String palabra : palabras) {
            tabla.insertar(palabra);
        }
        System.out.println("Frecuencia de 'hola': " + tabla.frecuencia("hola"));   // Output: 2
        System.out.println("Frecuencia de 'mundo': " + tabla.frecuencia("mundo")); // Output: 3
        System.out.println("Frecuencia de 'adios': " + tabla.frecuencia("adios")); // Output: 1
    }

}
