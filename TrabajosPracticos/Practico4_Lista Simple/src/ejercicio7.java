/* Ejercicio 7 – Invertir la lista
Escribe un método invertir() que invierta el orden de los nodos en la lista.
● Ejemplo: [10 -> 20 -> 30 -> 40] debe transformarse en [40 -> 30 -> 20-> 10].
*/


public class ejercicio7 {
    public static void main(String[] args) {
        listaEnlazada lista = new listaEnlazada();

        // Insertar valores al final
        lista.insertarFinal(10);
        lista.insertarFinal(20);
        lista.insertarFinal(30);
        lista.insertarFinal(40);

        System.out.println("Lista original:");
        lista.imprimirLista();

        // Invertir la lista
        lista.invertir();

        System.out.println("Lista invertida:");
        lista.imprimirLista();
    }
}