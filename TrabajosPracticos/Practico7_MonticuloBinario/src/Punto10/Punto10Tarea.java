public class Punto10Tarea {
    String descripcion;
    int prioridad; // menor numero = mas urgente
    
    public Punto10Tarea(String descripcion, int prioridad) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }
    
    @Override
    public String toString() {
        return "[" + prioridad + "] " + descripcion;
    }
}