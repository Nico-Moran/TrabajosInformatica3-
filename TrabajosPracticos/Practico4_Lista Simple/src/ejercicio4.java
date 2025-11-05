/* Ejercicio 4 – Eliminar por valor
Implementa el método eliminar(int valor) que elimine el primer nodo que contenga
ese valor.
● Prueba con la lista [10 -> 20 -> 30 -> 40] eliminando el 30.
● Verifica el resultado: [10 -> 20 -> 40].
*/


public class ejercicio4 {
    public static void main(String[] args) {
        listaEnlazada lista = new listaEnlazada();

        // Insertar valores al final
        lista.insertarFinal(10);
        lista.insertarFinal(20);
        lista.insertarFinal(30);
        lista.insertarFinal(40);

        System.out.println("Lista original:");
        lista.imprimirLista();

        // Eliminar el valor 30
        lista.eliminar(30);

        System.out.println("Lista después de eliminar 30:");
        lista.imprimirLista();
    }
}