import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        pizzeria pizzeria = new pizzeria();
        ordenador ordenador = new ordenador();
        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE PIZZERÍA ===");
            System.out.println("1. Agregar pedido");
            System.out.println("2. Mostrar todos los pedidos");
            System.out.println("3. Eliminar pedido");
            System.out.println("4. Actualizar precio de pedido");
            System.out.println("5. Actualizar tiempo de pedido");
            System.out.println("6. Ordenar por tiempo (Inserción)");
            System.out.println("7. Ordenar por precio (Shellsort)");
            System.out.println("8. Ordenar por nombre (Quicksort)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch(opcion) {
                case 1:
                    System.out.print("Nombre del cliente: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Precio total: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Tiempo preparación (min): ");
                    int tiempo = scanner.nextInt();
                    
                    pedido nuevoPedido = new pedido(nombre, precio, tiempo);
                    pizzeria.agregarPedido(nuevoPedido);
                    break;
                    
                case 2:
                    pizzeria.mostrarPedidos();
                    break;
                    
                case 3:
                    pizzeria.mostrarPedidos();
                    System.out.print("Índice del pedido a eliminar: ");
                    int indiceEliminar = scanner.nextInt();
                    pizzeria.eliminarPedido(indiceEliminar);
                    break;
                    
                case 4:
                    pizzeria.mostrarPedidos();
                    System.out.print("Índice del pedido a actualizar: ");
                    int indicePrecio = scanner.nextInt();
                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = scanner.nextDouble();
                    pizzeria.actualizarPrecio(indicePrecio, nuevoPrecio);
                    break;
                    
                case 5:
                    pizzeria.mostrarPedidos();
                    System.out.print("Índice del pedido a actualizar: ");
                    int indiceTiempo = scanner.nextInt();
                    System.out.print("Nuevo tiempo (min): ");
                    int nuevoTiempo = scanner.nextInt();
                    pizzeria.actualizarTiempo(indiceTiempo, nuevoTiempo);
                    break;
                    
                case 6:
                    ordenador.ordenarInsercion(pizzeria.getListaPedidos());
                    System.out.println("Pedidos ordenados por tiempo de preparación.");
                    pizzeria.mostrarPedidos();
                    break;
                    
                case 7:
                    ordenador.ordenarShellsort(pizzeria.getListaPedidos());
                    System.out.println("Pedidos ordenados por precio.");
                    pizzeria.mostrarPedidos();
                    break;
                    
                case 8:
                    ordenador.ordenarPorNombreQuicksort(pizzeria.getListaPedidos());
                    System.out.println("Pedidos ordenados por nombre.");
                    pizzeria.mostrarPedidos();
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                    
                default:
                    System.out.println("Opción inválida.");
            }
        } while(opcion != 0);
        
        scanner.close();
    }
}