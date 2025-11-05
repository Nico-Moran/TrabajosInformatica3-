

public class NodoAlumno {
    String nombre;
    int legajo;
    NodoAlumno siguiente;

    public NodoAlumno(String nombre, int legajo) {
        this.nombre = nombre;
        this.legajo = legajo;
        this.siguiente = null;
    }
}