
import java.util.ArrayList;

public class GestorTareas {
  private ArrayList<Tarea> listaTareas;

  public GestorTareas() {
    this.listaTareas = new ArrayList<>();
  }

  public void agregarTarea(Tarea tarea) {
    listaTareas.add(tarea);
  }

  public void listarTareas() {
    if (listaTareas.isEmpty()) {
      System.out.println("\nNo hay tareas.");
      return;
    }
    System.out.println("\nLista de tareas:");
    for (int i = 0; i < listaTareas.size(); i++) {
      System.out.println((i + 1) + ". " + listaTareas.get(i));
    }
  }

  public boolean marcarComoCompletada(int indice) {
    if (indice >= 0 && indice < listaTareas.size()) {
      listaTareas.get(indice).setEstado(EstadoTarea.COMPLETADO);
      return true;
    }
    return false;
  }

  public void eliminarCompletadas() {
    listaTareas.removeIf(t -> t.getEstado() == EstadoTarea.COMPLETADO);
    System.out.println("Tareas completadas eliminadas.");
  }
}