/* Ejercicio 7 – Números de Fibonacci optimizados
    Escriba una función recursiva para calcular el n-ésimo número de Fibonacci.
    Discuta con la IA cómo mejorar la eficiencia (por ejemplo, con memoización). */

import java.util.Scanner;

public class ejercicio7 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Ingrese el valor de n para calcular el n-ésimo número de Fibonacci: ");
        int n = scanner.nextInt();
        int[] memo = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }

        int fibonacciN = fibonacciMemoizado(n, memo);
        System.out.println("El " + n + "-ésimo número de Fibonacci es: " + fibonacciN);

        scanner.close();
    }

    public static int fibonacciMemoizado(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }
        if (memo[n] != -1) {
            return memo[n];
        }

        memo[n] = fibonacciMemoizado(n - 1, memo) + fibonacciMemoizado(n - 2, memo);
        return memo[n];
    }
}