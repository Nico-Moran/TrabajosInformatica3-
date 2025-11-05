/*Ejercicio 5 – Conversión binaria
Escriba un método recursivo que reciba un número entero positivo y devuelva su
representación en binario (como string).
Ejemplo: 13 → "1101". */

import java.util.Scanner;

public class ejercicio5 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Ingrese un número entero positivo: ");
        int numero = scanner.nextInt();

        if (numero < 0) {
            System.out.println("Por favor, ingrese un número entero positivo.");
        } else {
            String binario = convertirABinario(numero);
            System.out.println("La representación binaria de " + numero + " es: " + binario);
        }

        scanner.close();
    }

    public static String convertirABinario(int numero) {
        if (numero == 0) {
            return "0";
        } else if (numero == 1) {
            return "1";
        } else {
            return convertirABinario(numero / 2) + (numero % 2);
        }
    }
}