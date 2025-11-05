import java.time.LocalDateTime;
import java.util.Objects;

public class Turno {
    public String id;
    public String dniPaciente;
    public String matriculaMedico;
    public LocalDateTime fechaHora;
    public int duracionMin;
    public String motivo;
    
    public Turno(String id, String dniPaciente, String matriculaMedico, 
                LocalDateTime fechaHora, int duracionMin, String motivo) {
        this.id = id;
        this.dniPaciente = dniPaciente;
        this.matriculaMedico = matriculaMedico;
        this.fechaHora = fechaHora;
        this.duracionMin = duracionMin;
        this.motivo = motivo;
    }
    
    public LocalDateTime getFin() {
        return fechaHora.plusMinutes(duracionMin);
    }
    
    public boolean seSuperponeCon(Turno otro) {
        return !(this.getFin().isBefore(otro.fechaHora) || 
                otro.getFin().isBefore(this.fechaHora));
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return Objects.equals(id, turno.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Turno{id='" + id + "', paciente='" + dniPaciente + "', medico='" + matriculaMedico + 
               "', fechaHora=" + fechaHora + ", duracion=" + duracionMin + "min}";
    }
}