/* Ejercicio 8 – Buscar en un arreglo
    Implemente un método recursivo que determine si un número se encuentra dentro de un
    arreglo de enteros.
Ejemplo: [3, 5, 7, 9], buscar 7 → true. */

import java.util.Scanner;

public class ejercicio8 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Ingrese la cantidad de elementos del arreglo: ");
        int n = scanner.nextInt();
        int[] arreglo = new int[n];

        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < n; i++) {
            arreglo[i] = scanner.nextInt();
        }

        System.out.print("Ingrese el número a buscar: ");

        int numeroABuscar = scanner.nextInt();
        boolean encontrado = buscarEnArreglo(arreglo, n, numeroABuscar);

        if (encontrado) {
            System.out.println("El número " + numeroABuscar + " se encuentra en el arreglo.");
        } else {
            System.out.println("El número " + numeroABuscar + " no se encuentra en el arreglo.");
        }

        scanner.close();
    }

    public static boolean buscarEnArreglo(int[] arreglo, int n, int numeroABuscar) {
        if (n <= 0) {
            return false;
        }
        if (arreglo[n - 1] == numeroABuscar) {
            return true;
        }

        return buscarEnArreglo(arreglo, n - 1, numeroABuscar);
    }
}