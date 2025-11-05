/* Ejercicio 6 – Contar elementos
Implementa el método contar() que devuelva la cantidad de nodos en la lista.
● Para [1 -> 2 -> 3 -> 4 -> 5], el resultado debe ser 5.
*/


public class ejercicio6 {
    public static void main(String[] args) {
        listaEnlazada lista = new listaEnlazada();

        // Insertar valores al final
        lista.insertarFinal(1);
        lista.insertarFinal(2);
        lista.insertarFinal(3);
        lista.insertarFinal(4);
        lista.insertarFinal(5);

        // Contar la cantidad de nodos
        int cantidadNodos = contar(lista);
        System.out.println("Cantidad de nodos en la lista: " + cantidadNodos);
    }

    public static int contar(listaEnlazada lista) {
        int contador = 0;
        NodoListaSimple actual = lista.getCabeza();
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }
}