import java.time.LocalDateTime;
import java.util.*;

public class RecordatorioPlanner {
    private List<Recordatorio> recordatorios;
    
    public RecordatorioPlanner() {
        this.recordatorios = new ArrayList<>();
    }
    
    public void programar(Recordatorio r) {
        recordatorios.add(r);
    }
    
    public Recordatorio proximo() {
        if (recordatorios.isEmpty()) return null;
        
        Recordatorio masProximo = recordatorios.get(0);
        for (Recordatorio r : recordatorios) {
            if (r.fecha.isBefore(masProximo.fecha)) {
                masProximo = r;
            }
        }
        recordatorios.remove(masProximo);
        return masProximo;
    }
    
    public void reprogramar(String id, LocalDateTime nuevaFecha) {
        for (Recordatorio r : recordatorios) {
            if (r.id.equals(id)) {
                r.fecha = nuevaFecha;
                break;
            }
        }
    }
    
    public int size() {
        return recordatorios.size();
    }
}