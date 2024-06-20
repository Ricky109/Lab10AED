package hash;

class Empleado implements Comparable<Empleado> {
    int codigo;
    String nombre;
    String direccion;

    public Empleado(int codigo, String nombre, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    @Override
    public int compareTo(Empleado other) {
        return Integer.compare(this.codigo, other.codigo);
    }

    @Override
    public String toString() {
        return "Empleado [codigo=" + codigo + ", nombre=" + nombre + ", direccion=" + direccion + "]";
    }
}

