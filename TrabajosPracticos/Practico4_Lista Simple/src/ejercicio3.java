/* Ejercicio 3 – Insertar al final
Agrega a la clase ListaEnlazada el método insertarFinal(int dato).
● Inserta los valores 1, 2, 3.
● Imprime la lista y verifica que se agregan en orden.
*/



public class ejercicio3 {
    public static void main(String[] args) {
        listaEnlazada lista = new listaEnlazada();

        // Insertar valores al final
        lista.insertarFinal(1);
        lista.insertarFinal(2);
        lista.insertarFinal(3);

        // Imprimir la lista completa
        lista.imprimirLista();
    }
}