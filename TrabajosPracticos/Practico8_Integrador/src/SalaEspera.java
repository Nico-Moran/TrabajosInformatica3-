

public class SalaEspera {
    private String[] pacientes;
    private int front;
    private int rear;
    private int size;
    private int capacidad;
    
    public SalaEspera(int capacidad) {
        this.capacidad = capacidad;
        this.pacientes = new String[capacidad];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }
    
    public void llega(String dni) {
        if (size == capacidad) {
            front = (front + 1) % capacidad;
            size--;
        }
        
        rear = (rear + 1) % capacidad;
        pacientes[rear] = dni;
        size++;
    }
    
    public String atiende() {
        if (size == 0) {
            return null;
        }
        
        String dni = pacientes[front];
        front = (front + 1) % capacidad;
        size--;
        return dni;
    }
    
    public String peek() {
        if (size == 0) {
            return null;
        }
        return pacientes[front];
    }
    
    public int size() {
        return size;
    }
    
    public boolean estaLlena() {
        return size == capacidad;
    }
    
    public boolean estaVacia() {
        return size == 0;
    }
    
    public String[] getPacientes() {
        String[] resultado = new String[size];
        for (int i = 0; i < size; i++) {
            resultado[i] = pacientes[(front + i) % capacidad];
        }
        return resultado;
    }
}