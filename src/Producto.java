public class Producto implements Comparable<Producto> {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
        this.precio = 0;
    }

    // Orden natural por precio, de menor a mayor
    @Override
    public int compareTo(Producto otroProducto) {
        if (this.precio < otroProducto.precio)
            return -1;
        else if (this.precio > otroProducto.precio)
            return 1;
        else
            return 0;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("%-20s $%10.2f", nombre, precio);
    }
}
