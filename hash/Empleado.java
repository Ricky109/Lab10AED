package hash;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Empleado implements Comparable<Empleado> {
    protected int codigo;
    protected String nombre;
    protected String direccion;
    protected static int contador = 0;

    public Empleado(int codigo, String nombre, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        contador++; // Incrementar el contador al crear un nuevo empleado
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public static int getContador() {
        return contador;
    }


    @Override
    public int compareTo(Empleado other) {
        return Integer.compare(this.codigo, other.codigo);
    }

    @Override
    public String toString() {
        return "Empleado [codigo=" + codigo + ", nombre=" + nombre + ", direccion=" + direccion + "]";
    }
    
    public class GestorEmpleados {
        private String archivoDatos;

        // Constructor
        public GestorEmpleados(String archivoDatos) {
            this.archivoDatos = archivoDatos;
        }

        // Método para agregar un empleado al archivo
        public void agregarEmpleado(Empleado empleado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoDatos, true))) {
                writer.write(empleado.toString());
                writer.newLine();
                
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
            }
        }
    }
}
