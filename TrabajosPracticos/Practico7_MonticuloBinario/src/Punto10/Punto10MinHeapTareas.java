public class Punto10MinHeapTareas {
    private Punto10Tarea[] heap;
    private int size;
    
    public Punto10MinHeapTareas(int capacidad) {
        heap = new Punto10Tarea[capacidad + 1];
        size = 0;
    }
    
    // 1. Agregar tareas
    public void agregarTarea(Punto10Tarea t) {
        if(size == heap.length - 1) {
            System.out.println("Agenda llena. No se puede agregar mas tareas.");
            return;
        }
        heap[++size] = t;
        percolateUp(size);
        System.out.println("Tarea agregada: " + t);
    }
    
    // 2. Ver la proxima tarea urgente (peek)
    public Punto10Tarea verProximaTarea() {
        if(size == 0) {
            System.out.println("No hay tareas pendientes");
            return null;
        }
        return heap[1];
    }
    
    // 3. Completar la tarea mas urgente (poll)
    public Punto10Tarea completarTarea() {
        if(size == 0) {
            System.out.println("No hay tareas para completar");
            return null;
        }
        Punto10Tarea completada = heap[1];
        heap[1] = heap[size--];
        if(size > 0) {
            percolateDown(1);
        }
        System.out.println("Tarea completada: " + completada);
        return completada;
    }
    
    // 4. Mostrar todas las tareas pendientes en orden de prioridad
    public void mostrarTareasPendientes() {
        if(size == 0) {
            System.out.println("No hay tareas pendientes");
            return;
        }
        
        System.out.println("\nTAREAS PENDIENTES (orden de prioridad):");
        System.out.println("----------------------------------------");
        
        // Crear copia temporal y extraer sin imprimir mensajes
        Punto10MinHeapTareas copia = copiarHeap();
        int numero = 1;
        
        while(!copia.estaVacia()) {
            Punto10Tarea tarea = copia.extraerSilencioso(); // Nuevo metodo sin mensajes
            System.out.println(numero + ". " + tarea);
            numero++;
        }
        System.out.println("----------------------------------------");
    }
    
    // Metodo para extraer sin imprimir mensajes (para mostrar tareas)
    private Punto10Tarea extraerSilencioso() {
        if(size == 0) return null;
        Punto10Tarea tarea = heap[1];
        heap[1] = heap[size--];
        if(size > 0) {
            percolateDown(1);
        }
        return tarea;
    }
    
    private Punto10MinHeapTareas copiarHeap() {
        Punto10MinHeapTareas copia = new Punto10MinHeapTareas(heap.length - 1);
        for(int i = 1; i <= size; i++) {
            copia.heap[i] = this.heap[i];
        }
        copia.size = this.size;
        return copia;
    }
    
    public boolean estaVacia() {
        return size == 0;
    }
    
    public int tareasPendientes() {
        return size;
    }
    
    private void percolateUp(int i) {
        while(i > 1 && heap[i].prioridad < heap[i/2].prioridad) {
            Punto10Tarea temp = heap[i];
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
            
            Punto10Tarea temp = heap[i];
            heap[i] = heap[hijoMin];
            heap[hijoMin] = temp;
            
            i = hijoMin;
        }
    }
}