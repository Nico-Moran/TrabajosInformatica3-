public abstract class Accion {
    protected AgendaMedico agenda;
    
    public Accion(AgendaMedico agenda) {
        this.agenda = agenda;
    }
    
    public abstract void ejecutar();
    public abstract void deshacer();
}