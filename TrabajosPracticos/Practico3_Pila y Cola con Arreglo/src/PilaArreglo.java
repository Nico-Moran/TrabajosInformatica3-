
public class PilaArreglo<T> {
    private T[] datos;
    private int tope;

    @SuppressWarnings("unchecked")
    public PilaArreglo(int capacidad) {
        datos = (T[]) new Object[capacidad];
        tope = -1;
    }

    public boolean isEmpty() {
        return tope == -1;
    }

    public boolean isFull() {
        return tope == datos.length - 1;
    }

    public void push(T dato) {
        if (isFull()) {
            System.out.println("La pila está llena.");
            return;
        }
        datos[++tope] = dato;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.");
            return null;
        }
        return datos[tope--];
    }

    public T top() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.");
            return null;
        }
        return datos[tope];
    }

    public void mostrar() {
        System.out.print("Pila: ");
        for (int i = 0; i <= tope; i++) {
            System.out.print(datos[i] + " ");
        }
        System.out.println();
    }
}