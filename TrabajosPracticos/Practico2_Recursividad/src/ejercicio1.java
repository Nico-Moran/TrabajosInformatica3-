/* 
Ejercicio 1 – Conteo de dígitos
Escriba una función recursiva que calcule cuántos dígitos tiene un número entero positivo.
Ejemplo: 12345 → 5. 
*/

import java.util.Scanner;

public class ejercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número entero positivo: ");
        int numero = scanner.nextInt();

        if (numero < 0) {
            System.out.println("Por favor, ingrese un número entero positivo.");
        } else {
            int cantidadDigitos = contarDigitos(numero);
            System.out.println("El número " + numero + " tiene " + cantidadDigitos + " dígitos.");
        }

        scanner.close();
    }

    public static int contarDigitos(int numero) {
        if (numero < 10) {
            return 1;
        } else {
            return 1 + contarDigitos(numero / 10);
        }
    }
}