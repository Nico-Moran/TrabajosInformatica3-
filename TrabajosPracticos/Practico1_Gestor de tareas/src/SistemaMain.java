
import java.util.Scanner;

public class SistemaMain {

  static Scanner sc = new Scanner(System.in);
  static GestorTareas tareas = new GestorTareas();
  
  public static void main(String[] args) {
    menu();
  }

  static void menu() {
    int opc = 0;
    do {
      System.out.println("\nBIENVENIDO AL GESTOR DE TAREAS");
      System.out.println("1. Agregar tareas");
      System.out.println("2. Listar tareas");
      System.out.println("3. Marcar tarea como completada");
      System.out.println("4. Eliminar tareas completadas");
      System.out.println("5. Salir\n");
      if (sc.hasNextInt()) {
        opc = sc.nextInt();
        sc.nextLine();
      } else {
        System.out.println("Ingrese un número válido.");
        sc.nextLine();
        continue;
      }

      switch (opc) {
        case 1 -> agregarTarea();
        case 2 -> tareas.listarTareas();
        case 3 -> marcarTareaCompletada();
        case 4 -> tareas.eliminarCompletadas();
        case 5 -> System.out.println("\nSaliendo...");
        default -> System.out.println("\nOpción inválida. Por favor, ingrese un número entre 1 y 5.");
      }
    } while (opc != 5);
  }

  static void agregarTarea() {
    String desc;
    int estadoInt;
    EstadoTarea estadoEnum;

    do {
      System.out.print("\nIngrese la descripción de la tarea: ");
      desc = sc.nextLine().trim();
      if(desc.isEmpty()) {
        System.out.println("La descripción no puede estar vacía.");
      }
    } while (desc.isEmpty());

    System.out.println("Ingrese el estado de la tarea:\n1 - Completado\n2 - Pendiente");
    if (sc.hasNextInt()) {
      estadoInt = sc.nextInt();
      sc.nextLine();
    } else {
      System.out.println("Debe ingresar 1 o 2.");
      sc.nextLine();
      return;
    }

    try {
      estadoEnum = EstadoTarea.desdeValor(estadoInt);
      Tarea nuevaTarea = new Tarea(desc, estadoEnum);
      tareas.agregarTarea(nuevaTarea);

      System.out.println("Tarea agregada correctamente.\n");
    } catch (IllegalArgumentException e) {
      System.out.println("Estado inválido. Debe ingresar 1 o 2.\n");
    }
  }

  static void marcarTareaCompletada() {
    tareas.listarTareas();
    System.out.print("\nIngrese el número de la tarea a marcar como completada: ");
    if (sc.hasNextInt()) {
      int idx = sc.nextInt();
      sc.nextLine();
      if (tareas.marcarComoCompletada(idx - 1)) {
        System.out.println("Tarea marcada como completada.\n");
      } else {
        System.out.println("Índice inválido.\n");
      }
    } else {
      System.out.println("Debe ingresar un número.");
      sc.nextLine();
    }
  }
}