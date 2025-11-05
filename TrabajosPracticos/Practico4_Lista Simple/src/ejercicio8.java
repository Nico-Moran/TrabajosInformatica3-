/* Ejercicio 8 – Insertar en posición
Crea un método insertarEn(int pos, int valor) que inserte un nodo en la posición
indicada (0 = inicio).
● Ejemplo: en [1 -> 2 -> 4], al insertar 3 en la posición 2, debe quedar [1 -> 2-> 3 -> 4].
*/



public class ejercicio8 {
    public static void main(String[] args) {
        listaEnlazada lista = new listaEnlazada();

        // Insertar valores al final
        lista.insertarFinal(1);
        lista.insertarFinal(2);
        lista.insertarFinal(4);

        System.out.println("Lista original:");
        lista.imprimirLista();

        // Insertar 3 en la posición 2
        insertarEn(lista, 2, 3);

        System.out.println("Lista después de insertar 3 en la posición 2:");
        lista.imprimirLista();
    }

    public static void insertarEn(listaEnlazada lista, int pos, int valor) {
        if (pos == 0) {
            lista.insertarInicio(valor);
            return;
        }

        NodoListaSimple actual = lista.getCabeza();
        int indice = 0;

        while (actual != null && indice < pos - 1) {
            actual = actual.siguiente;
            indice++;
        }

        if (actual != null) {
            NodoListaSimple nuevoNodo = new NodoListaSimple(valor);
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        } else {
            System.out.println("Posición fuera de los límites de la lista.");
        }
    }
}