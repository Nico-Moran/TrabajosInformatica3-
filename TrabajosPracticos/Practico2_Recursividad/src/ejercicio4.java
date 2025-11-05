/* Ejercicio 4 – Máximo común divisor (MCD)
Implemente de manera recursiva el algoritmo de Euclides para calcular el MCD de dos
números enteros positivos.
Ejemplo: MCD(48, 18) = 6. */

import java.util.Scanner;

public class ejercicio4 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Ingrese el primer número entero positivo: ");
        int a = scanner.nextInt();
        System.out.print("Ingrese el segundo número entero positivo: ");
        int b = scanner.nextInt();

        if (a <= 0 || b <= 0) {
            System.out.println("Por favor, ingrese números enteros positivos.");
        } else {
            int mcd = calcularMCD(a, b);
            System.out.println("El MCD de " + a + " y " + b + " es: " + mcd);
        }

        scanner.close();
    }

    public static int calcularMCD(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return calcularMCD(b, a % b);
        }
    }
}