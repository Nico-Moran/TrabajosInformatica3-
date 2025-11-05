import java.time.LocalDateTime;
import java.util.*;

public class PlanificadorQuirofanoImpl {
    private PriorityQueue<Quirofano> quirofanosLibres;
    private Map<String, Integer> minutosPorMedico;
    
    public PlanificadorQuirofanoImpl(List<String> idsQuirofanos) {
        this.quirofanosLibres = new PriorityQueue<>();
        this.minutosPorMedico = new HashMap<>();
        
        for (String id : idsQuirofanos) {
            quirofanosLibres.offer(new Quirofano(id));
        }
    }
    
    public void procesar(SolicitudCirugia solicitud) {
        Quirofano quirofano = quirofanosLibres.poll();
        if (quirofano == null) {
            System.out.println("ERROR: No hay quirofanos disponibles");
            return;
        }
        
        LocalDateTime inicioCirugia = quirofano.finOcupado.isBefore(LocalDateTime.now()) ? 
                                    LocalDateTime.now() : quirofano.finOcupado;
        LocalDateTime finCirugia = inicioCirugia.plusMinutes(solicitud.durMin);
        
        if (finCirugia.isAfter(solicitud.deadline)) {
            System.out.println("ADVERTENCIA: No cumple deadline");
        }
        
        quirofano.finOcupado = finCirugia;
        quirofanosLibres.offer(quirofano);
        
        // Actualizar minutos del médico
        int minutosActuales = minutosPorMedico.getOrDefault(solicitud.matricula, 0);
        minutosPorMedico.put(solicitud.matricula, minutosActuales + solicitud.durMin);
        
        System.out.println("Cirugia asignada a " + quirofano.id);
    }
    
    public List<String> topKMedicosBloqueados(int K) {
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(minutosPorMedico.entrySet());
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        List<String> resultado = new ArrayList<>();
        for (int i = 0; i < Math.min(K, lista.size()); i++) {
            resultado.add(lista.get(i).getKey() + " - " + lista.get(i).getValue() + " min");
        }
        return resultado;
    }
    
    static class Quirofano implements Comparable<Quirofano> {
        String id;
        LocalDateTime finOcupado;
        
        public Quirofano(String id) {
            this.id = id;
            this.finOcupado = LocalDateTime.now();
        }
        
        @Override
        public int compareTo(Quirofano otro) {
            return this.finOcupado.compareTo(otro.finOcupado);
        }
    }
}