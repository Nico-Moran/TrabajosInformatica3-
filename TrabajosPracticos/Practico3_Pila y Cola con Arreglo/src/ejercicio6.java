/*Ejercicio 6 – Deshacer/Rehacer con Pila
Implemente un programa que simule un editor de texto.
● Cada acción del usuario (escribir, borrar, copiar) se guarda en una pila de deshacer.
● Cuando el usuario presiona Deshacer, se pasa la acción a una pila de rehacer.
📌 Simule al menos 5 acciones y muestre cómo cambian las pilas al deshacer y
rehacer. */
import java.util.Stack;

public class ejercicio6 {
    public static void main(String[] args) {
        Stack<String> deshacer = new Stack<>();
        Stack<String> rehacer = new Stack<>();

        deshacer.push("Escribir 'Hola'");
        deshacer.push("Agregar 'Mundo'");
        deshacer.push("Borrar 'o'");
        deshacer.push("Escribir '!'");
        deshacer.push("Copiar texto");

        System.out.println("\nPila Deshacer inicial: " + deshacer);
        System.out.println("Pila Rehacer inicial: " + rehacer);

        System.out.println("\n-- Deshacer 2 acciones --");
        for (int i = 0; i < 2; i++) {
            String accion = deshacer.pop();
            rehacer.push(accion);
        }

        System.out.println("Deshacer: " + deshacer);
        System.out.println("Rehacer: " + rehacer);

        System.out.println("\n-- Rehacer 1 acción --");
        deshacer.push(rehacer.pop());

        System.out.println("Deshacer: " + deshacer);
        System.out.println("Rehacer: " + rehacer);
    }
}