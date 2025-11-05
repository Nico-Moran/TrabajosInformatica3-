/* Ejercicio 9 – Eliminar duplicados
Implementa un método eliminarDuplicados() que recorra la lista y elimine los nodos
repetidos.
● Ejemplo: [1 -> 2 -> 2 -> 3 -> 1] debe quedar [1 -> 2 -> 3].
*/



public class ejercicio9 {
    public static void main(String[] args) {
        listaEnlazada lista = new listaEnlazada();

        // Insertar valores con duplicados
        lista.insertarFinal(1);
        lista.insertarFinal(2);
        lista.insertarFinal(2);
        lista.insertarFinal(3);
        lista.insertarFinal(1);

        System.out.println("Lista original:");
        lista.imprimirLista();

        // Eliminar duplicados
        eliminarDuplicados(lista);

        System.out.println("Lista después de eliminar duplicados:");
        lista.imprimirLista();
    }

    public static void eliminarDuplicados(listaEnlazada lista) {
        NodoListaSimple actual = lista.getCabeza();

        while (actual != null) {
            NodoListaSimple runner = actual;
            while (runner.siguiente != null) {
                if (runner.siguiente.dato == actual.dato) {
                    runner.siguiente = runner.siguiente.siguiente; // Eliminar nodo duplicado;
                } else {
                    runner = runner.siguiente;
                }
            }
            actual = actual.siguiente;
        }
    }
}