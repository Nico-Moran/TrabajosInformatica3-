/*Ejercicio 4 – Simulación de Turnos con Cola
Implemente un programa que utilice ColaArreglo para simular una fila de espera en un
banco.
● Los clientes llegan en el orden: Ana, Luis, Marta, Pedro.
● Se atienden los dos primeros clientes.
📌 Mostrar la cola antes y después de atender. */
public class ejercicio4 {
    public static void main(String[] args) {
        ColaArreglo<String> cola = new ColaArreglo<>(10);

        cola.enqueue("Ana");
        cola.enqueue("Luis");
        cola.enqueue("Marta");
        cola.enqueue("Pedro");

        System.out.println("Cola inicial:");
        cola.mostrar();

        cola.dequeue();
        cola.dequeue();

        System.out.println("Cola después de atender:");
        cola.mostrar();
    }
}