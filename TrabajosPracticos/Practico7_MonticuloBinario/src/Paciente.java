public class Paciente {
    String nombre;
    int prioridad; // 1 = alta, 2 = media, 3 = baja
    
    public Paciente(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }
    
    @Override
    public String toString() {
        String prioridadStr = "";
        switch(prioridad) {
            case 1: prioridadStr = "ALTA"; break;
            case 2: prioridadStr = "MEDIA"; break;
            case 3: prioridadStr = "BAJA"; break;
        }
        return nombre + " (" + prioridadStr + ")";
    }
}