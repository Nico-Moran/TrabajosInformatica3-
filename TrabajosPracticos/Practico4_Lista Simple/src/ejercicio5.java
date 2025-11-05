/* Ejercicio 5 – Buscar un valor
Crea un método buscar(int valor) que recorra la lista y devuelva true si encuentra el
nodo.
● Prueba con la lista [5 -> 15 -> 25 -> 35].
● Busca el 25 (debe devolver true) y el 100 (debe devolver false).
*/


public class ejercicio5 {
    public static void main(String[] args) {
        listaEnlazada lista = new listaEnlazada();

        // Insertar valores al final
        lista.insertarFinal(5);
        lista.insertarFinal(15);
        lista.insertarFinal(25);
        lista.insertarFinal(35);

        // Buscar el valor 25
        boolean encontrado25 = buscar(lista, 25);
        System.out.println("¿Se encontró el 25? " + encontrado25);

        // Buscar el valor 100
        boolean encontrado100 = buscar(lista, 100);
        System.out.println("¿Se encontró el 100? " + encontrado100);
    }

    public static boolean buscar(listaEnlazada lista, int valor) {
        NodoListaSimple actual = lista.getCabeza();
        while (actual != null) {
            if (actual.dato == valor) {
                return true; // Valor encontrado;
            }
            actual = actual.siguiente;
        }
        return false; // Valor no encontrado;
    }
}