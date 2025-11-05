/* 
Ejercicio 3 – Suma de elementos de un arreglo
Implemente una función recursiva que calcule la suma de todos los elementos de un arreglo
de enteros.
Ejemplo: [2, 4, 6, 8] → 20.
👉 Extienda la solución para que además devuelva el promedio usando únicamente
recursión. 
*/

import java.util.Scanner;

public class ejercicio3 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Ingrese la cantidad de elementos del arreglo: ");
        int n = scanner.nextInt();
        int[] arreglo = new int[n];

        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < n; i++) {
            arreglo[i] = scanner.nextInt();
        }

        int suma = sumarArreglo(arreglo, n);
        double promedio = (double) suma / n;

        System.out.println("La suma de los elementos del arreglo es: " + suma);
        System.out.println("El promedio de los elementos del arreglo es: " + promedio);

        scanner.close();
    }

    public static int sumarArreglo(int[] arreglo, int n) {
        if (n <= 0) {
            return 0;
        } else {
            return arreglo[n - 1] + sumarArreglo(arreglo, n - 1);
        }
    }
}