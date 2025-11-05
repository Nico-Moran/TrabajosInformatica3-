import java.time.LocalDateTime;

public class SolicitudCirugia {
    public String id;
    public String matricula;
    public int durMin;
    public LocalDateTime deadline;
    
    public SolicitudCirugia(String id, String matricula, int durMin, LocalDateTime deadline) {
        this.id = id;
        this.matricula = matricula;
        this.durMin = durMin;
        this.deadline = deadline;
    }
}