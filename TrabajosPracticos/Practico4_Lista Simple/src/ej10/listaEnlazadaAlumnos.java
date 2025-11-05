
public class listaEnlazadaAlumnos {
    private NodoAlumno cabeza;

    public listaEnlazadaAlumnos() {
        this.cabeza = null;
    }

    public void agregarAlumno(String nombre, int legajo) {
        NodoAlumno nuevoNodo = new NodoAlumno(nombre, legajo);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoAlumno actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    public void eliminarAlumno(int legajo) {
        if (cabeza == null) {
            return; // Lista vacía
        }

        if (cabeza.legajo == legajo) {
            cabeza = cabeza.siguiente; // Eliminar la cabeza;
            return;
        }

        NodoAlumno actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.legajo != legajo) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente; // Eliminar el nodo;
        }
    }

    public NodoAlumno getCabeza() {
        return cabeza;
    }
}