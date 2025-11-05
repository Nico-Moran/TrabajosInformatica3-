public class MinHeap {

    private int [] heap;
    private int size;

    public MinHeap(int capacidad){
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
        int min = heap[1];
        System.out.println("[===ELIMINANDO===]");
        System.out.println("El minimo a eliminar: "+min+" (posicion 1)");
        System.out.println("Ultimo elemento es: "+heap[size]+" (posicion "+size+" )");
        //Movemos el ultimo elemento a la raiz
        heap[1]=heap[size--];
        System.out.println("Despues de mover el ultimo a la raiz: ");
        printHeapEstructura();

        percolateDown(1);
        return min;
    }
    /* 
     *                                                                           [===PERCOLATE DOWN/UP===]
    */
    private void percolateDown(int i){

        System.out.println("Percolando para abajo desde la posicion "+i+" (valor "+heap[i]+" )");
        while(2 * i <= size){
            int hijoIzquierda= 2*i;
            int hijoDerecha= 2 * i +1;
            int hijoMin = hijoIzquierda;//Le asiganamos al principio suponiendo que es el mas chico

            //Buscamos el hijo mas chico
            if(hijoDerecha <size && heap[hijoDerecha]<heap[hijoIzquierda]){
                hijoMin = hijoDerecha;
                System.out.println("Hijo derecho " + heap[hijoDerecha] + " es menor que hijo izquierdo " + heap[hijoIzquierda]);
            }
            
            System.out.println("Comprobamos a: " + heap[i] + " (papa) vs " + heap[hijoMin] + " (hijo menor)");
             // Si el papa es menor o igual que el hijo menor, terminamos
            if (heap[i] <= heap[hijoMin]) {
                System.out.println("El padre es menor o igual - Proceso Termninado");
                break;
            }
            // Si no Intercambiamos papa con hijo menor
            System.out.println("Intercambiamos: " + heap[i] + " (posicion " + i + ") ↔ " + heap[hijoMin] + " (posicion " + hijoMin + ")");

            int temp = heap[i];
            heap[i] = heap[hijoMin];
            heap[hijoMin] = temp;
            
            i = hijoMin;
            System.out.println("  Nueva posición: " + i);
        }
        System.out.println("Percolacion para abajo terminado.");
    }
    private void percolateUp(int i){
        System.out.println("Percolando para arriba desde la posicion: "+size);

        while(i>1 && heap[i] < heap[i/2]){
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
     *                                                                            [===VER EL MAXIMO/MINIMO===]
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
        System.out.print("Heap: ");
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
    public void printHeapEstructura() {
    System.out.println("Estructura del heap:");
    if (size == 0) {
        System.out.println("(gheap vacio)");
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
        
        // iomprimnir hijo izquierdo
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
        
        System.out.println("Heap en forma de arbol:");
        
        int nivel = 0;
        int elementosEnNivel = 1; // Elementos en el nivel actual
        int contador = 0; // Contador de elementos impresos en el nivel actual
        
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i] + " ");
            contador++;
            
            // Si imprimimos todos los elementos de la fila 
            if (contador == elementosEnNivel) {
                System.out.println(); // Nueva linea para el sig nivel/fila
                nivel++;
                elementosEnNivel = (int) Math.pow(2, nivel); // 2^nivel elementos
                contador = 0;
            }
        }
        
        // Si el ultimo nivel no se completo, asegurar nueva línea
        if (contador > 0) {
            System.out.println();
        }
    }
    /* 
     *                                                                           [===SEGUIMIENTO DEL ESTADO INTERNO===]
    */
    public void printArray() {
        System.out.print("Arreglo interno: [");
        for (int i = 0; i < heap.length; i++) {
            if (i == 0) {
                System.out.print("_"); // indice 0 no usado
            } else if (i <= size) {
                System.out.print(heap[i]); // elementos validos
            } else {
                System.out.print("_"); // espacios vacios
            }
            
            if (i < heap.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println("Tamaño: " + size + ", Capacidad: " + (heap.length - 1));
    }

    /* 
     *                                                                           [===CONSTRUCTOR ALTERNATIVO  HEAPIFY===]
    */
    public MinHeap(int[] datos) {
        System.out.println("Arreglo original: " + java.util.Arrays.toString(datos));
        
        heap = new int[datos.length + 1]; // +1 por el indice 1 como raiz
        size = datos.length;
        
        // Copiar datos al heap
        for (int i = 0; i < datos.length; i++) {
            heap[i + 1] = datos[i];
        }
        
        System.out.println("Heap antes de heapify:");
        printTree();
        
        // Aplicar heapify desde el ultimo nodo no-hoja hacia la raiz
        for (int i = size / 2; i >= 1; i--) {
            System.out.println("\n--- Heapify en posicion " + i + " (valor: " + heap[i] + ") ---");
            heapify(i);
            printTree();
        }
    }
    
    private void heapify(int i) {
        System.out.println("  Aplicando heapify en posicion " + i);
        
        int menor = i;
        int hijoIzq = 2 * i;
        int hijoDer = 2 * i + 1;
        
        // Comparar con hijo izquierdo
        if (hijoIzq <= size && heap[hijoIzq] < heap[menor]) {
            System.out.println("Hijo izquierdo " + heap[hijoIzq] + " es menor que " + heap[menor]);
            menor = hijoIzq;
        }
        
        // Comparar con hijo derecho
        if (hijoDer <= size && heap[hijoDer] < heap[menor]) {
            System.out.println("Hijo derecho " + heap[hijoDer] + " es menor que " + heap[menor]);
            menor = hijoDer;
        }
        
        // Si el menor no es el padre, intercambiar y continuar
        if (menor != i) {
            System.out.println("Intercambiando: " + heap[i] + " ↔ " + heap[menor]);
            int temp = heap[i];
            heap[i] = heap[menor];
            heap[menor] = temp;
            
            // Llamar recursivamente en la nueva posición
            heapify(menor);
        } else {
            System.out.println("Posicion " + i + " ya cumple propiedad del heap");
        }
    }
    /* 
     *                                                                           [===METODO ESTATICO HEAPSORT===]
    */
    public static void heapsort(int[] arr) {
        System.out.println("=== HEAPSORT ===");
        System.out.println("Arreglo original: " + java.util.Arrays.toString(arr));
        
        // Crear heap con capacidad suficiente
        MinHeap heap = new MinHeap(arr.length);
        
        System.out.println("\n--- FASE 1: Insertar todos los elementos ---");
        // Insertar todos los elementos en el heap
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Insertando: " + arr[i]);
            heap.add(arr[i]);
            heap.printTree();
        }
        
        System.out.println("\n--- FASE 2: Extraer en orden ---");
        // Extraer elementos en orden (mínimo primero)
        for (int i = 0; i < arr.length; i++) {
            int min = heap.poll();
            arr[i] = min;
            System.out.println("Extraido: " + min + " → posicion " + i);
            System.out.println("Arreglo parcial: " + java.util.Arrays.toString(arr));
            if (!heap.isEmpty()) {
                heap.printTree();
            }
        }
        
        System.out.println("Arreglo ordenado: " + java.util.Arrays.toString(arr));
    }
   
}