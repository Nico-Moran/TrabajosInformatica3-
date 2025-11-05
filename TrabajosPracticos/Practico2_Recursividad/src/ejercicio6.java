/* Ejercicio 6 – Palíndromo

Cree una función recursiva que determine si una cadena es un palíndromo.
Ejemplo: "neuquen" → true, "informatica" → false. */

import java.util.Scanner;

public class ejercicio6 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Ingrese una cadena de texto: ");
        String cadena = scanner.nextLine();

        boolean esPalindromo = esPalindromo(cadena);
        if (esPalindromo) {
            System.out.println("La cadena \"" + cadena + "\" es un palíndromo.");
        } else {
            System.out.println("La cadena \"" + cadena + "\" no es un palíndromo.");
        }

        scanner.close();
    }

    public static boolean esPalindromo(String cadena) {
        cadena = cadena.replaceAll("\\s+", "").toLowerCase();

        if (cadena.length() <= 1) {
            return true;
        }

        if (cadena.charAt(0) != cadena.charAt(cadena.length() - 1)) {
            return false;
        }

        return esPalindromo(cadena.substring(1, cadena.length() - 1));
    }
}