import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class Almacen {
    private LinkedList<Producto> listaProductos;
    private Producto objetoProducto;
    private BufferedReader entrada;

    public Almacen() {
        listaProductos = new LinkedList<>();
        entrada = new BufferedReader(new InputStreamReader(System.in));
    }

    public void visualizarMenu() throws IOException {
        int opcion = 0;
        do {
            System.out.println("\n===== MENU ALMACEN =====");
            System.out.println("1. Agregar producto");
            System.out.println("2. Ordenar lista de productos");
            System.out.println("3. Visualizar lista de productos");
            System.out.println("4. Buscar producto por precio");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");
            try {
                opcion = Integer.parseInt(entrada.readLine().trim());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1: agregarProducto(); break;
                case 2: ordenarListaProductos(); break;
                case 3: visualizarListaProductos(); break;
                case 4: buscarProducto(); break;
                case 5: System.out.println("Fin del programa."); break;
                default: System.out.println("Opcion no valida.");
            }
        } while (opcion != 5);
    }

    public void agregarProducto() throws IOException {
        System.out.print("Nombre del producto: ");
        String nombre = entrada.readLine().trim();
        System.out.print("Precio del producto: ");
        double precio;
        try {
            precio = Double.parseDouble(entrada.readLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Precio no valido, no se agrego el producto.");
            return;
        }

        objetoProducto = new Producto(nombre, precio);
        listaProductos.add(objetoProducto);
        System.out.println("Producto agregado.");
    }

    public void ordenarListaProductos() {
        // Usa el compareTo de Producto (orden por precio)
        Collections.sort(listaProductos);
        System.out.println("Lista ordenada por precio (menor a mayor).");
    }

    public void visualizarListaProductos() {
        if (listaProductos.isEmpty()) {
            System.out.println("La lista esta vacia.");
            return;
        }
        System.out.println(String.format("\n%-4s%-20s %11s", "#", "Nombre", "Precio"));
        for (int i = 0; i < listaProductos.size(); i++) {
            System.out.println((i + 1) + ".  " + listaProductos.get(i));
        }
    }

    public void buscarProducto() throws IOException {
        if (listaProductos.isEmpty()) {
            System.out.println("La lista esta vacia.");
            return;
        }
        System.out.print("Precio a buscar: ");
        double precioBuscado;
        try {
            precioBuscado = Double.parseDouble(entrada.readLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Precio no valido.");
            return;
        }

        // La busqueda binaria solo funciona si la lista esta ordenada
        Collections.sort(listaProductos);
        int posicion = busquedaBinaria(precioBuscado, 0, listaProductos.size() - 1);

        if (posicion == -1) {
            System.out.println("No existe un producto con ese precio.");
        } else {
            System.out.println("Producto encontrado en la posicion " + (posicion + 1) + ":");
            System.out.println(listaProductos.get(posicion));
        }
    }

    // Busqueda binaria recursiva por precio
    private int busquedaBinaria(double precioBuscado, int inicio, int fin) {
        if (inicio > fin)
            return -1;

        int medio = (inicio + fin) / 2;
        double precioMedio = listaProductos.get(medio).getPrecio();

        if (precioMedio == precioBuscado)
            return medio;
        else if (precioBuscado < precioMedio)
            return busquedaBinaria(precioBuscado, inicio, medio - 1);
        else
            return busquedaBinaria(precioBuscado, medio + 1, fin);
    }
}
