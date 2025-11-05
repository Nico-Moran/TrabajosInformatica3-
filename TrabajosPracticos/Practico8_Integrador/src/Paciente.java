import java.util.Objects;

public class Paciente {
    public String dni;
    public String nombre;
    
    public Paciente(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(dni, paciente.dni);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
    
    @Override
    public String toString() {
        return "Paciente{dni='" + dni + "', nombre='" + nombre + "'}";
    }
}