/*Ejercicio 7 – Simulación de Impresora con Cola
Una impresora recibe documentos en orden de llegada.
● Cada documento tiene un número (ej: Doc1, Doc2, Doc3).
● Se procesan en orden usando una cola.
📌 Simule la llegada de 5 documentos y la impresión de 3 de ellos. */
public class ejercicio7{
    public static void main(String[] args){
        ColaArreglo<String> cola = new ColaArreglo<>(10);

        for (int i = 1; i <= 5; i++) {
            cola.enqueue("Doc" + i);
        }

        System.out.println("Cola de impresión inicial:");
        cola.mostrar();

        for (int i = 0; i < 3; i++) {
            System.out.println("Imprimiendo " + cola.dequeue());
        }

        System.out.println("Cola después de imprimir:");
        cola.mostrar();
    }
}