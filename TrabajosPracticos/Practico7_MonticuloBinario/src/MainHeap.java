import java.util.Scanner;

public class MainHeap {
    public static void main(String[] args) {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);
        

        while (opcion!=5){
            MinHeap heap = new MinHeap(10);
            System.out.println("Bienvenido al Sistema de Practico Monticulo Binario.");
            System.out.println("1] Ejercicio 1 - Crear estructura básica de MinHeap");
            System.out.println("2] Ejercicio 2 - Implementar percolateUp");
            System.out.println("3] Ejercicio 3 - Implementar percolateDown");
            System.out.println("4] Ejercicio 4 - Mostrar el heap en forma de árbol");
            System.out.println("5] Ejercicio 5 - Construcción desde un arreglo (heapify)");
            System.out.println("6] Ejercicio 6 - Implementar Heapsort");
            System.out.println("7] Ejercicio 7 - Implementar MaxHeap");
            System.out.println("8] Ejercicio 8 - Cola de prioridad de pacientes");
            System.out.println("9] Ejercicio 9 - Seguimiento del estado interno");
            System.out.println("Ingresar una de las opciones: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: 
                    System.out.println("a) Implementá una clase MinHeap en Java que permita almacenar enteros en un montículo binario mínimo utilizando un arreglo.");
                    System.out.println("   Probá agregar los valores: 20, 5, 15, 3, 11 y mostrálos en orden de extracción.");
                    
                    heap.add(20);
                    heap.printHeapEstructura();

                    heap.add(5);
                    heap.printHeapEstructura();
                    
                    heap.add(15);
                    heap.printHeapEstructura();
                    
                    heap.add(3);
                    heap.printHeapEstructura();
                    
                    heap.add(11);
                    heap.printHeapEstructura();
                    
                    // Mostrar en orden de extracción
                    System.out.println("\nExtracción en orden:");
                    while (!heap.isEmpty()) {
                        System.out.print(heap.poll() + " ");
                    }
                    break;
                case 2:
                    System.out.println("Completá el método add(int valor) para que, al insertar un nuevo elemento, este percole hacia arriba manteniendo la propiedad del montículo. Mostrá por consola cada intercambio que se realiza durante la inserción.");
                   
                    int[] valoresA = {20, 5, 15, 3, 11};
                    
                    for (int valor : valoresA) {
                        System.out.println("--- Insertando " + valor + " ---");
                        heap.add(valor);
                        
                        heap.printHeapEstructura();
                    }
                    break;
                case 3:
                    System.out.println("Completá el método poll() para que, al eliminar el mínimo (la raíz), el último elemento " +
                                                "suba y luego \"percole hacia abajo\" hasta restaurar la propiedad del montículo. " +
                                                "Mostrá el contenido del arreglo antes y después de cada eliminación.");
                   
                    int[] valoresB = {20, 5, 15, 3, 11};
                     for (int valor : valoresB) {
                        heap.add(valor);
                    }
                    heap.printHeap();
                    heap.printHeapEstructura();
                    
                    // Extraer todos los elementos mostrando el proceso
                    System.out.println("\n=== EXTRACCIONES ===");
                    while (!heap.isEmpty()) {
                        int min = heap.poll();
                        System.out.println("Extraído: " + min);
                        if (!heap.isEmpty()) {
                            heap.printHeap();
                            heap.printHeapEstructura();
                        }
                    }
                    break;
                case 4:
                    System.out.println("Agregá a la clase un método:void printTree(); que muestre el montículo en forma jerárquica (una línea por nivel), para visualizar su"+
                                        " estructura después de cada inserción y eliminación.");
                   
                    int[] valoresC = {20, 5, 15, 3, 11};
                    for (int valor : valoresC) {
                        System.out.println("--- Despues de insertar " + valor + " ---");
                        heap.add(valor);
                        heap.printTree();
                     }
                    while (!heap.isEmpty()) {
                        int min = heap.poll();
                        System.out.println("\n--- Despues de extraer " + min + " ---");
                        if (!heap.isEmpty()) {
                            heap.printTree();
                        } else {
                            System.out.println("(heap vacio)");
                        }
                    }
                    break;
                    case 5:
                    System.out.println("Implementá un constructor alternativo:\r\n" + //
                                                "public MinHeap(int[] datos)\r\n" + //
                                                "que reciba un arreglo y construya el heap utilizando el método heapify (bottom-up).\r\n" + //
                                                "Mostrá paso a paso cómo se reorganiza el arreglo original hasta formar un montículo\r\n" + //
                                                "válido.");
                   
                    int[] datos = {20, 5, 15, 3, 11, 8, 25};
                    System.out.println("Construyendo heap desde arreglo: " + java.util.Arrays.toString(datos));
                    MinHeap heapA = new MinHeap(datos);
                    
                    System.out.println("\n=== HEAP FINAL ===");
                    heapA.printTree();
                    
                    System.out.println("\n=== EXTRACCIÓN PARA VERIFICAR ===");
                    System.out.print("Extracción en orden: ");
                    while (!heapA.isEmpty()) {
                        System.out.print(heapA.poll() + " ");
                    }    
                    break;
                case 6:
                   System.out.println("Utilizando tu clase MinHeap, escribí un método estático:public static void heapsort(int[] arr)");
                    int[] arr = {9, 4, 7, 1, 6, 2};
                    MinHeap.heapsort(arr);
                break;
                case 7:
                   System.out.println("Crea una nueva clase MaxHeap similar a MinHeap, pero que mantenga en la raíz el mayor\r\n" + //
                                              "valor.\r\n" + //
                                              "Mostrá el orden de eliminación (de mayor a menor) con el arreglo:\r\n" + //
                                              "{10, 3, 15, 8, 6, 12}.");
                    int[] datosA = {10, 3, 15, 8, 6, 12};
                    MaxHeap maxHeap = new MaxHeap(datosA);
                    
                    System.out.println("===ORDEN DE ELIMINACION (de mayor a menor) ===");
                    System.out.print("Extraccion: ");
                    while (!maxHeap.isEmpty()) {
                        System.out.print(maxHeap.poll() + " ");
                    }
                break;
                case 8:
                   System.out.println("Crea una nueva clase Paciente y y usá un MinHeap para implementar una cola de prioridad médica con los métodos:\r\n" + //
                                              "void ingresar(Paciente p);\r\n" + //
                                              "Paciente atender();");
                    MinHeapPacientes colaHospital = new MinHeapPacientes(10);
        
                    // Simular ingreso de 5 pacientes
                    System.out.println("=== INGRESO DE PACIENTES ===");
                    
                    colaHospital.ingresar(new Paciente("Juan Pérez", 2));   // MEDIA
                    colaHospital.mostrarCola();
                    
                    colaHospital.ingresar(new Paciente("María García", 1)); // ALTA
                    colaHospital.mostrarCola();
                    
                    colaHospital.ingresar(new Paciente("Carlos López", 3)); // BAJA
                    colaHospital.mostrarCola();
                    
                    colaHospital.ingresar(new Paciente("Ana Martínez", 1)); // ALTA
                    colaHospital.mostrarCola();
                    
                    colaHospital.ingresar(new Paciente("Luis Rodríguez", 2)); // MEDIA
                    colaHospital.mostrarCola();
                    
                    // Simular atencion de pacientes
                    System.out.println("\n=== ATENCIÓN DE PACIENTES ===");
                    
                    while(colaHospital.hayPacientes()) {
                        Paciente atendido = colaHospital.atender();
                        System.out.println("Atendido: " + atendido);
                        colaHospital.mostrarCola();
                    }
                    
                    System.out.println("\n=== TODOS LOS PACIENTES ATENDIDOS ===");
                
                    break;
                case 9:
                   System.out.println("Agrega al MinHeap un metodo: void printArray();");
                    MinHeap heapB = new MinHeap(8);
        
                    System.out.println("=== INSERCIONES ===");
                    int[] valores = {20, 5, 15, 3, 11, 8};
                    
                    for (int valor : valores) {
                        heapB.add(valor);
                        heapB.printArray();
                        heapB.printTree();
                        System.out.println();
                    }
                    
                    System.out.println("=== ELIMINACIONES ===");
                    while (!heapB.isEmpty()) {
                        heapB.poll();
                        if (!heapB.isEmpty()) {
                            heapB.printArray();
                            heapB.printTree();
                            System.out.println();
                        }
                    }
                    
                    System.out.println("=== HEAP VACIO ===");
                    heapB.printArray();
                break;
                default:
                    break;
            }   
        }
        scanner.close();
    }
}
