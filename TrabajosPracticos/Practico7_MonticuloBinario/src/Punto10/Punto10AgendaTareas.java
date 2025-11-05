import java.util.Scanner;

public class Punto10AgendaTareas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Punto10MinHeapTareas agenda = new Punto10MinHeapTareas(20);
        
        System.out.println("AGENDA DE TAREAS CON PRIORIDAD");
        System.out.println("==============================");
        
        boolean ejecutando = true;
        
        while(ejecutando) {
            System.out.println("\nMENU PRINCIPAL:");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Ver proxima tarea urgente");
            System.out.println("3. Completar tarea mas urgente");
            System.out.println("4. Mostrar todas las tareas pendientes");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch(opcion) {
                case 1:
                    System.out.print("Descripcion de la tarea: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Prioridad (1=urgente, 2=media, 3=baja): ");
                    int prioridad = scanner.nextInt();
                    agenda.agregarTarea(new Punto10Tarea(descripcion, prioridad));
                    break;
                    
                case 2:
                    Punto10Tarea proxima = agenda.verProximaTarea();
                    if(proxima != null) {
                        System.out.println("PROXIMA TAREA URGENTE: " + proxima);
                    }
                    break;
                    
                case 3:
                    agenda.completarTarea();
                    System.out.println("Tareas pendientes: " + agenda.tareasPendientes());
                    break;
                    
                case 4:
                    agenda.mostrarTareasPendientes();
                    break;
                    
                case 5:
                    ejecutando = false;
                    System.out.println("Hasta pronto!");
                    break;
                    
                default:
                    System.out.println("Opcion invalida");
            }
        }
        
        scanner.close();
    }
}