public class listaEnlazada {
    private NodoListaSimple cabeza;

    public listaEnlazada() {
        this.cabeza = null;
    }

    public void insertarInicio(int dato) {
        NodoListaSimple nuevoNodo = new NodoListaSimple(dato);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
    }

    public void imprimirLista() {
        NodoListaSimple actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }

    public void insertarFinal(int dato) {
        NodoListaSimple nuevoNodo = new NodoListaSimple(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoListaSimple actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    public void eliminar(int valor) {
        if (cabeza == null) {
            return; // Lista vacía
        }

        if (cabeza.dato == valor) {
            cabeza = cabeza.siguiente; // Eliminar la cabeza;
            return;
        }

        NodoListaSimple actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.dato != valor) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente; // Eliminar el nodo;
        }
    }

    public NodoListaSimple getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoListaSimple cabeza) {
        this.cabeza = cabeza;
    }

    public void invertir() {
        NodoListaSimple anterior = null;
        NodoListaSimple actual = cabeza;
        NodoListaSimple siguiente = null;

        while (actual != null) {
            siguiente = actual.siguiente; // Guardar el siguiente nodo;
            actual.siguiente = anterior;  // Invertir el enlace;
            anterior = actual;            // Mover anterior y actual un paso adelante;
            actual = siguiente;
        }
        cabeza = anterior; // Actualizar la cabeza de la lista;
    }
}