import java.time.LocalDateTime;
import java.util.*;

public class AgendaConHistorialImpl extends AgendaMedicoAVL implements AgendaConHistorial {
    private Stack<Accion> pilaUndo;
    private Stack<Accion> pilaRedo;
    
    public AgendaConHistorialImpl() {
        super();
        this.pilaUndo = new Stack<>();
        this.pilaRedo = new Stack<>();
    }
    
    @Override
    public boolean agendar(Turno t) {
        boolean exito = super.agendar(t);
        if (exito) {
            AccionAgendar accion = new AccionAgendar(this, t);
            pilaUndo.push(accion);
            pilaRedo.clear();
        }
        return exito;
    }
    
    @Override
    public boolean cancelar(String idTurno) {
        Turno turno = buscarTurnoPorId(idTurno);
        boolean exito = super.cancelar(idTurno);
        if (exito && turno != null) {
            AccionCancelar accion = new AccionCancelar(this, turno);
            pilaUndo.push(accion);
            pilaRedo.clear();
        }
        return exito;
    }
    
    @Override
    public boolean reprogramar(String idTurno, LocalDateTime nuevaFecha) {
        Turno turno = buscarTurnoPorId(idTurno);
        if (turno == null) return false;
        
        LocalDateTime fechaAnterior = turno.fechaHora;
        boolean exitoCancelar = super.cancelar(idTurno);
        if (!exitoCancelar) return false;
        
        Turno nuevoTurno = new Turno(turno.id, turno.dniPaciente, 
                                   turno.matriculaMedico, nuevaFecha,
                                   turno.duracionMin, turno.motivo);
        boolean exitoAgendar = super.agendar(nuevoTurno);
        
        if (exitoAgendar) {
            AccionReprogramar accion = new AccionReprogramar(this, idTurno, fechaAnterior, nuevaFecha);
            pilaUndo.push(accion);
            pilaRedo.clear();
        }
        return exitoAgendar;
    }
    
    @Override
    public boolean undo() {
        if (pilaUndo.isEmpty()) return false;
        
        Accion accion = pilaUndo.pop();
        accion.deshacer();
        pilaRedo.push(accion);
        return true;
    }
    
    @Override
    public boolean redo() {
        if (pilaRedo.isEmpty()) return false;
        
        Accion accion = pilaRedo.pop();
        accion.ejecutar();
        pilaUndo.push(accion);
        return true;
    }
    
    private Turno buscarTurnoPorId(String idTurno) {
        return buscarTurnoEnArbol(root, idTurno);
    }
    
    private Turno buscarTurnoEnArbol(AVLNode node, String idTurno) {
        if (node == null) return null;
        if (node.turno.id.equals(idTurno)) return node.turno;
        
        Turno izquierda = buscarTurnoEnArbol(node.left, idTurno);
        if (izquierda != null) return izquierda;
        
        return buscarTurnoEnArbol(node.right, idTurno);
    }
}

class AccionAgendar extends Accion {
    private Turno turno;
    private boolean ejecutado;
    
    public AccionAgendar(AgendaMedico agenda, Turno turno) {
        super(agenda);
        this.turno = turno;
        this.ejecutado = false;
    }
    
    @Override
    public void ejecutar() {
        if (!ejecutado) {
            agenda.agendar(turno);
            ejecutado = true;
        }
    }
    
    @Override
    public void deshacer() {
        if (ejecutado) {
            agenda.cancelar(turno.id);
            ejecutado = false;
        }
    }
}

class AccionCancelar extends Accion {
    private Turno turno;
    private boolean ejecutado;
    
    public AccionCancelar(AgendaMedico agenda, Turno turno) {
        super(agenda);
        this.turno = turno;
        this.ejecutado = false;
    }
    
    @Override
    public void ejecutar() {
        if (!ejecutado) {
            agenda.cancelar(turno.id);
            ejecutado = true;
        }
    }
    
    @Override
    public void deshacer() {
        if (ejecutado) {
            agenda.agendar(turno);
            ejecutado = false;
        }
    }
}

class AccionReprogramar extends Accion {
    private String idTurno;
    private LocalDateTime fechaAnterior;
    private LocalDateTime fechaNueva;
    private boolean ejecutado;
    
    public AccionReprogramar(AgendaMedico agenda, String idTurno, 
                           LocalDateTime fechaAnterior, LocalDateTime fechaNueva) {
        super(agenda);
        this.idTurno = idTurno;
        this.fechaAnterior = fechaAnterior;
        this.fechaNueva = fechaNueva;
        this.ejecutado = true;
    }
    
    @Override
    public void ejecutar() {
        if (!ejecutado) {
            if (agenda instanceof AgendaConHistorialImpl) {
                ((AgendaConHistorialImpl) agenda).reprogramar(idTurno, fechaNueva);
            }
            ejecutado = true;
        }
    }
    
    @Override
    public void deshacer() {
        if (ejecutado) {
            if (agenda instanceof AgendaConHistorialImpl) {
                ((AgendaConHistorialImpl) agenda).reprogramar(idTurno, fechaAnterior);
            }
            ejecutado = false;
        }
    }
}

interface AgendaConHistorial extends AgendaMedico {
    boolean reprogramar(String idTurno, LocalDateTime nuevaFecha);
    boolean undo();
    boolean redo();
}