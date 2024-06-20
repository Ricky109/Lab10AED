package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestArchivo{
    public static void main(String[] args) {
        String fileName = "D:/EMPLEADO.TXT";
        HashC<String> hashTable = new HashC<>(fileName);
        System.out.println(hashTable);
        
        String fileName2 = "D:/EMPLEADO2.TXT";
        int numberOfRecords = getNumberOfRecords(fileName2);
        HashA<Empleado> hashTableA = new HashA<>(numberOfRecords);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName2))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int codigo = Integer.parseInt(parts[0].trim());
                String nombre = parts[1].trim();
                String direccion = parts[2].trim();
                Empleado empleado = new Empleado(codigo, nombre, direccion);
                hashTableA.insert(codigo, empleado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(hashTableA);
    }
    
    private static int getNumberOfRecords(String fileName) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
