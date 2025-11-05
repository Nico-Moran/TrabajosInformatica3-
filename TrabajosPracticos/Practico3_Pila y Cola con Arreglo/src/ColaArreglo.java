
public class ColaArreglo<T> {
    private T[] datos;
    private int frente;
    private int fin;
    private int cantidad;

    @SuppressWarnings("unchecked")
    public ColaArreglo(int capacidad) {
        datos = (T[]) new Object[capacidad];
        frente = 0;
        fin = -1;
        cantidad = 0;
    }

    public boolean isEmpty() {
        return cantidad == 0;
    }

    public boolean isFull() {
        return cantidad == datos.length;
    }

    public void enqueue(T dato) {
        if (isFull()) {
            System.out.println("La cola está llena.");
            return;
        }
        fin = (fin + 1) % datos.length;
        datos[fin] = dato;
        cantidad++;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return null;
        }
        T dato = datos[frente];
        frente = (frente + 1) % datos.length;
        cantidad--;
        return dato;
    }

    public T top() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return null;
        }
        return datos[frente];
    }

    public void mostrar() {
        System.out.print("Cola: ");
        for (int i = 0; i < cantidad; i++) {
            int index = (frente + i) % datos.length;
            System.out.print(datos[index] + " ");
        }
        System.out.println();
    }
}