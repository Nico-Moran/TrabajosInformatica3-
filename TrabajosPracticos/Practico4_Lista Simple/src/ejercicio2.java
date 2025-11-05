/* Ejercicio 2 – Insertar al inicio
Implementa el método insertarInicio(int dato) en la clase ListaEnlazada.
● Prueba insertando los valores: 10, 20, 30.
● Imprime la lista para verificar que el orden sea correcto.
*/



public class ejercicio2 {
    public static void main(String[] args) {
        listaEnlazada lista = new listaEnlazada();

        // Insertar valores al inicio
        lista.insertarInicio(10);
        lista.insertarInicio(20);
        lista.insertarInicio(30);

        // Imprimir la lista completa
        lista.imprimirLista();
    }
}