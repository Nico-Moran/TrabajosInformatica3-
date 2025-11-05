import java.util.*;

public class AgendaConsolidator {
    
    public static List<Turno> merge(List<Turno> agendaA, List<Turno> agendaB) {
        List<Turno> resultado = new ArrayList<>();
        Set<String> idsProcesados = new HashSet<>();
        Set<String> conflictosRegistrados = new HashSet<>();
        
        int i = 0, j = 0;
        while (i < agendaA.size() && j < agendaB.size()) {
            Turno turnoA = agendaA.get(i);
            Turno turnoB = agendaB.get(j);
            
            if (turnoA.fechaHora.isBefore(turnoB.fechaHora)) {
                procesarTurno(turnoA, resultado, idsProcesados, conflictosRegistrados);
                i++;
            } else {
                procesarTurno(turnoB, resultado, idsProcesados, conflictosRegistrados);
                j++;
            }
        }
        
        while (i < agendaA.size()) {
            procesarTurno(agendaA.get(i), resultado, idsProcesados, conflictosRegistrados);
            i++;
        }
        
        while (j < agendaB.size()) {
            procesarTurno(agendaB.get(j), resultado, idsProcesados, conflictosRegistrados);
            j++;
        }
        
        return resultado;
    }
    
    private static void procesarTurno(Turno turno, List<Turno> resultado, 
                                    Set<String> idsProcesados, Set<String> conflictosRegistrados) {
        if (idsProcesados.contains(turno.id)) {
            if (!conflictosRegistrados.contains(turno.id)) {
                System.out.println("CONFLICTO: Turno duplicado ID=" + turno.id);
                conflictosRegistrados.add(turno.id);
            }
            return;
        }
        
        for (Turno existente : resultado) {
            if (existente.matriculaMedico.equals(turno.matriculaMedico) && 
                existente.seSuperponeCon(turno)) {
                String conflictoKey = turno.id + "-" + existente.id;
                if (!conflictosRegistrados.contains(conflictoKey)) {
                    System.out.println("CONFLICTO: Superposicion horaria - Medico: " + 
                                     turno.matriculaMedico + ", Turnos: " + 
                                     turno.id + " y " + existente.id);
                    conflictosRegistrados.add(conflictoKey);
                }
                return;
            }
        }
        
        resultado.add(turno);
        idsProcesados.add(turno.id);
    }
}