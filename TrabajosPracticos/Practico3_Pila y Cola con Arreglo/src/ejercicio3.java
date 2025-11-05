/*Ejercicio 3 – Invertir una Cadena con Pila
Usando la clase PilaArreglo, escriba un programa que reciba una cadena y la invierta.
Ejemplo: "Hola" → "aloH". */
import java.util.Scanner;

public class ejercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese una cadena: ");
        String texto = sc.nextLine();

        PilaArreglo<String> pila = new PilaArreglo<>(texto.length());
        for (char c : texto.toCharArray()) {
            pila.push(String.valueOf(c));
        }

        System.out.print("Cadena invertida: ");
        while (!pila.isEmpty()) {
            System.out.print(pila.pop());
        }
        System.out.println();
        sc.close();
    }
}