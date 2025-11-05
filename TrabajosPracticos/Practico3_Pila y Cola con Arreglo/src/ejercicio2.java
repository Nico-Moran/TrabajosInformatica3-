/*Ejercicio 2 – Implementación de Cola
Implemente una clase ColaArreglo en Java utilizando un arreglo.
Métodos: enqueue(int dato), dequeue(), top(), isEmpty(), isFull().
Pruebe encolando los enteros 1, 2, 3, 4 y desencolando uno.
 */
public class ejercicio2 {
    public static void main(String[] args) {
        ColaArreglo<Integer> cola = new ColaArreglo<>(5);

        cola.enqueue(1);
        cola.enqueue(2);
        cola.enqueue(3);
        cola.enqueue(4);
        cola.mostrar();

        System.out.println("Dequeue: " + cola.dequeue());
        cola.mostrar();
    }
}