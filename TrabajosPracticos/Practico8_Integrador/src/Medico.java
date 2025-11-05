import java.util.Objects;

public class Medico {
    public String matricula;
    public String nombre;
    public String especialidad;
    
    public Medico(String matricula, String nombre, String especialidad) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return Objects.equals(matricula, medico.matricula);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
    
    @Override
    public String toString() {
        return "Medico{matricula='" + matricula + "', nombre='" + nombre + "', especialidad='" + especialidad + "'}";
    }
}