public class MinHeapPacientes {
    private Paciente[] heap;
    private int size;
    
    public MinHeapPacientes(int capacidad) {
        heap = new Paciente[capacidad + 1]; // índice 1 como raiz
        size = 0;
    }
    
    
    public void ingresar(Paciente p) {
        if(size == heap.length - 1) {
            throw new IllegalStateException("Cola llena");
        }
        heap[++size] = p;
        System.out.println("Ingresando: " + p);
        percolateUp(size);
    }
    
    
    public Paciente atender() {
        if(size == 0) {
            throw new IllegalStateException("No hay pacientes");
        }
        Paciente prioritario = heap[1];
        System.out.println("\n[=== ATENDIENDO ===]");
        System.out.println("Paciente a atender: " + prioritario);
        
        heap[1] = heap[size--];
        if(size > 0) {
            percolateDown(1);
        }
        
        return prioritario;
    }
    
    private void percolateUp(int i) {
        while(i > 1 && heap[i].prioridad < heap[i/2].prioridad) {
            // Intercambiar
            Paciente temp = heap[i];
            heap[i] = heap[i/2];
            heap[i/2] = temp;
            i /= 2;
        }
    }
    
    private void percolateDown(int i) {
        while(2 * i <= size) {
            int hijoIzq = 2 * i;
            int hijoDer = 2 * i + 1;
            int hijoMin = hijoIzq;
            
            if(hijoDer <= size && heap[hijoDer].prioridad < heap[hijoIzq].prioridad) {
                hijoMin = hijoDer;
            }
            
            if(heap[i].prioridad <= heap[hijoMin].prioridad) {
                break;
            }
            
            // Intercambiar
            Paciente temp = heap[i];
            heap[i] = heap[hijoMin];
            heap[hijoMin] = temp;
            
            i = hijoMin;
        }
    }
    
    public boolean hayPacientes() {
        return size > 0;
    }
    
    public void mostrarCola() {
        System.out.println("\n--- COLA ACTUAL ---");
        if(size == 0) {
            System.out.println("(No hay pacientes)");
            return;
        }
        mostrarColaRecursivo(1, 0);
    }
    
    private void mostrarColaRecursivo(int i, int nivel) {
        if(i > size) return;
        
        mostrarColaRecursivo(2 * i + 1, nivel + 1);
        
        for(int j = 0; j < nivel; j++) System.out.print("   ");
        System.out.println(heap[i]);
        
        mostrarColaRecursivo(2 * i, nivel + 1);
    }
}