/*Ejercicio 8 – Cola Circular para Gestión de Llamadas
Implemente una cola circular para gestionar llamadas en un call center.
● La cola tiene capacidad máxima de 5 llamadas.
● Cuando llega una nueva llamada y la cola está llena, sobrescribe la más antigua.
📌 Simule la llegada de 8 llamadas y muestre el estado final de la cola. */

public class ejercicio8 {
    public static void main(String[] args) {
        int capacidad = 5;
        String[] cola = new String[capacidad];
        int inicio = 0;
        int fin = 0;
        int cantidad = 0;

        for (int i = 1; i <= 8; i++) {
            String llamada = "Llamada" + i;
            if (cantidad < capacidad) {
                cola[fin] = llamada;
                fin = (fin + 1) % capacidad;
                cantidad++;
            } else {
                cola[fin] = llamada;
                fin = (fin + 1) % capacidad;
                inicio = (inicio + 1) % capacidad;
            }
        }

        System.out.println("\nEstado final de la cola circular:");
        for (int i = 0; i < cantidad; i++) {
            System.out.print(cola[(inicio + i) % capacidad] + " ");
        }
        System.out.println();
    }
}