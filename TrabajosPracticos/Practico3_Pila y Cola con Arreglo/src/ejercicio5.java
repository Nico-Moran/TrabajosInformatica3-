/*Ejercicio 5 – Palíndromo con Pila y Cola
Un palíndromo es una palabra o frase que se lee igual en ambos sentidos (ej: "radar").
Implemente un programa que determine si una palabra es palíndromo usando:
● Una pila para recorrer de derecha a izquierda.
● Una cola para recorrer de izquierda a derecha. */
import java.util.Scanner;

public class ejercicio5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese una palabra: ");
        String palabra = sc.nextLine().toLowerCase();

        PilaArreglo<String> pila = new PilaArreglo<>(palabra.length());
        ColaArreglo<String> cola = new ColaArreglo<>(palabra.length());

        for (char c : palabra.toCharArray()) {
            pila.push(String.valueOf(c));
            cola.enqueue(String.valueOf(c));
        }

        boolean palindromo = true;
        while (!pila.isEmpty()) {
            if (!pila.pop().equals(cola.dequeue())) {
                palindromo = false;
                break;
            }
        }

        System.out.println(palindromo ? "Es palíndromo" : "No es palíndromo");
        sc.close();
    }
}