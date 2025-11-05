/*  Ejercicio 1 – Crear un nodo
Escribe una clase Nodo que almacene un número entero y un puntero al siguiente nodo.
● Implementa un programa que cree tres nodos y los enlace manualmente.
● Imprime la lista completa.
 */
public class ejercicio1 {
    public static void main(String[] args) {
        // Crear nodos
        NodoListaSimple nodo1 = new NodoListaSimple(10);
        NodoListaSimple nodo2 = new NodoListaSimple(20);
        NodoListaSimple nodo3 = new NodoListaSimple(30);

        // Enlazar nodos
        nodo1.siguiente = nodo2;
        nodo2.siguiente = nodo3;

        // Imprimir la lista completa
        NodoListaSimple actual = nodo1;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }
}