public class MaxHeap {

    private int [] heap;
    private int size;

    public MaxHeap(int capacidad){
        heap = new int[capacidad+1];//Indice 1 como raiz
        size =0;
    }
    /* 
     *                                                                           [===AGREGAR===]
    */
    public void add(int valor){
        if(size==heap.length-1){
            throw new IllegalStateException("Heap lleno");
        }
        heap[++size]=valor;
        System.out.println("Insertando: "+valor+" en la posicicon "+size);
        percolateUp(size);
    }
    /* 
     *                                                                           [===ELIMINAR/REMOVER===]
    */
    public int poll(){
        if(size == 0){
            throw new IllegalStateException("Heap vacio");
        }
        int max = heap[1];
        System.out.println("[===ELIMINANDO===]");
        System.out.println("El maximo a eliminar: "+max+" (posicion 1)");
        System.out.println("Ultimo elemento es: "+heap[size]+" (posicion "+size+" )");
        //Movemos el ultimo elemento a la raiz
        heap[1]=heap[size--];
        System.out.println("Despues de mover el ultimo a la raiz: ");
        printHeapEstructura();

        percolateDown(1);
        return max;
    }
    /* 
     *                                                                           [===PERCOLATE DOWN/UP===]
    */
    private void percolateDown(int i){
        System.out.println("Percolando para abajo desde la posicion "+i+" (valor "+heap[i]+" )");
        while(2 * i <= size){
            int hijoIzquierda= 2*i;
            int hijoDerecha= 2 * i +1;
            int hijoMax = hijoIzquierda;//Le asiganamos al principio suponiendo que es el mas grande

            //Buscamos el hijo mas grande (CAMBIADO: > en lugar de <)
            if(hijoDerecha <= size && heap[hijoDerecha] > heap[hijoIzquierda]){
                hijoMax = hijoDerecha;
                System.out.println("Hijo derecho " + heap[hijoDerecha] + " es mayor que hijo izquierdo " + heap[hijoIzquierda]);
            }
            
            System.out.println("Comprobamos a: " + heap[i] + " (papa) vs " + heap[hijoMax] + " (hijo mayor)");
             // Si el papa es mayor o igual que el hijo mayor, terminamos (CAMBIADO: >=)
            if (heap[i] >= heap[hijoMax]) {
                System.out.println("El padre es mayor o igual - Proceso Termninado");
                break;
            }
            // Si no Intercambiamos papa con hijo mayor
            System.out.println("Intercambiamos: " + heap[i] + " (posicion " + i + ") ↔ " + heap[hijoMax] + " (posicion " + hijoMax + ")");

            int temp = heap[i];
            heap[i] = heap[hijoMax];
            heap[hijoMax] = temp;
            
            i = hijoMax;
            System.out.println("  Nueva posición: " + i);
        }
        System.out.println("Percolacion para abajo terminado.");
    }
    
    private void percolateUp(int i){
        System.out.println("Percolando para arriba desde la posicion: "+i);

        // CAMBIADO: > en lugar de < (buscar mayor)
        while(i>1 && heap[i] > heap[i/2]){
            System.out.println("Intercambiando: "+heap[i]+" (posicion "+i+")<->"+heap[i/2]+"(posicion"+(i/2)+")");
            int temp = heap[i];
            heap[i]=heap[i/2];
            heap[i/2]=temp;
            i /= 2;
            System.out.println("Nueva posicion: "+i);
        }
        System.out.println("[-------Finalizado la percola-------]");
    }
    /* 
     *                                                                            [===VER EL MAXIMO===]
    */
    public int peek(){
        if(size == 0){
            throw new IllegalStateException("Heap vacio");
        }
        return heap[1];
    }

    public boolean isEmpty(){
        return size==0;
    }
    /* 
     *                                                                           [===IMPRIMIR===]
    */
    public void printHeap() {
        System.out.print("MaxHeap: ");
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
    
    public void printHeapEstructura() {
        System.out.println("Estructura del MaxHeap:");
        if (size == 0) {
            System.out.println("(heap vacio)");
            return;
        }
        printHeapEstructuraRecursivo(1, 0);
        System.out.println();
    }

    private void printHeapEstructuraRecursivo(int i, int nivel) {
        if (i > size) return;
        
        // Primero hijo derecho
        printHeapEstructuraRecursivo(2 * i + 1, nivel + 1);
        
        // Imprimir nodo actual
        for (int j = 0; j < nivel; j++) System.out.print("   ");
        System.out.println(heap[i]);
        
        // Imprimir hijo izquierdo
        printHeapEstructuraRecursivo(2 * i, nivel + 1);
    }
    /* 
     *                                                                           [===IMPRIMIR EN FORMA DE ARBOL===]
    */
    public void printTree() {
        if (size == 0) {
            System.out.println("(heap vacio)");
            return;
        }
        
        System.out.println("MaxHeap en forma de arbol:");
        
        int nivel = 0;
        int elementosEnNivel = 1;
        int contador = 0;
        
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i] + " ");
            contador++;
            
            if (contador == elementosEnNivel) {
                System.out.println();
                nivel++;
                elementosEnNivel = (int) Math.pow(2, nivel);
                contador = 0;
            }
        }
        
        if (contador > 0) {
            System.out.println();
        }
    }
    /* 
     *                                                                           [===CONSTRUCTOR ALTERNATIVO HEAPIFY===]
    */
    public MaxHeap(int[] datos) {
        System.out.println("=== CONSTRUCCION MAXHEAP HEAPIFY ===");
        System.out.println("Arreglo original: " + java.util.Arrays.toString(datos));
        
        heap = new int[datos.length + 1];
        size = datos.length;
        
        for (int i = 0; i < datos.length; i++) {
            heap[i + 1] = datos[i];
        }
        
        System.out.println("MaxHeap antes de heapify:");
        printTree();
        
        for (int i = size / 2; i >= 1; i--) {
            System.out.println("\n--- Heapify en posicion " + i + " (valor: " + heap[i] + ") ---");
            heapify(i);
            printTree();
        }
        
        System.out.println("=== MAXHEAP CONSTRUIDO ===");
    }
    
    private void heapify(int i) {
        System.out.println("  Aplicando heapify en posicion " + i);
        
        int mayor = i;
        int hijoIzq = 2 * i;
        int hijoDer = 2 * i + 1;
        
        // CAMBIADO: > en lugar de < (buscar mayor)
        if (hijoIzq <= size && heap[hijoIzq] > heap[mayor]) {
            System.out.println("Hijo izquierdo " + heap[hijoIzq] + " es mayor que " + heap[mayor]);
            mayor = hijoIzq;
        }
        
        // CAMBIADO: > en lugar de < (buscar mayor)
        if (hijoDer <= size && heap[hijoDer] > heap[mayor]) {
            System.out.println("Hijo derecho " + heap[hijoDer] + " es mayor que " + heap[mayor]);
            mayor = hijoDer;
        }
        
        if (mayor != i) {
            System.out.println("Intercambiando: " + heap[i] + " ↔ " + heap[mayor]);
            int temp = heap[i];
            heap[i] = heap[mayor];
            heap[mayor] = temp;
            
            heapify(mayor);
        } else {
            System.out.println("Posicion " + i + " ya cumple propiedad del max-heap");
        }
    }
}