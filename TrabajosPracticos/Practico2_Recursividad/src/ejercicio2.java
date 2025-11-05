/* 
Ejercicio 2 – Invertir una cadena
Escriba un método recursivo que reciba una cadena y devuelva la misma cadena invertida.
Ejemplo: "recursivo" → "ovisrucer". 
*/

import java.util.Scanner;

public class ejercicio2 {
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese una cadena de texto: ");
        String cadena = scanner.nextLine();

        String cadenaInvertida = invertirCadena(cadena);
        System.out.println("La cadena invertida es: " + cadenaInvertida);

        scanner.close();
    }

    public static String invertirCadena(String cadena) {
        if (cadena.isEmpty()) {
            return cadena;
        } else {
            return cadena.charAt(cadena.length() - 1) + invertirCadena(cadena.substring(0, cadena.length() - 1));
        }
    }
}