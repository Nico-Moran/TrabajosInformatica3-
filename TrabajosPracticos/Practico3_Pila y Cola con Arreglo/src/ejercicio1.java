/*Ejercicios
Ejercicio 1 – Implementación de Pila
Implemente una clase PilaArreglo en Java utilizando un arreglo.
Métodos: push(int dato), pop(), top(), isEmpty(), isFull().
Pruebe apilando los enteros 10, 20, 30, 40 y desapilando dos. */
public class ejercicio1 {
    public static void main(String[] args) {
        PilaArreglo<Integer> pila = new PilaArreglo<>(5);
        pila.push(10);
        pila.push(20);
        pila.push(30);
        pila.push(40);
        pila.mostrar();

        pila.mostrar();

        System.out.println("Pop: " + pila.pop());
        System.out.println("Pop: " + pila.pop());
        pila.mostrar();
    }
}