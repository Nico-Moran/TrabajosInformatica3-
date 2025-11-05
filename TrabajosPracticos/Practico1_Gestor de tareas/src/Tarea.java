
public class Tarea {
  String descripcion;
  EstadoTarea estado;

  public Tarea(String descripcion, EstadoTarea estado) {
    this.descripcion = descripcion;
    this.estado = estado;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public EstadoTarea getEstado() {
    return estado;
  }

  public void setEstado(EstadoTarea estado) {
    this.estado = estado;
  }

  @Override
  public String toString() {
    return "Descripción: " + descripcion + " | Estado: " + estado;
  }
}