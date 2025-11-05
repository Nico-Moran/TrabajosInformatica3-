
public enum EstadoTarea {
  COMPLETADO(1),
  PENDIENTE(2);

  private final int valor;

  EstadoTarea(int valor) {
    this.valor = valor;
  }

  public int getValor() {
    return valor;
  }

  public static EstadoTarea desdeValor(int valor) {
    for (EstadoTarea estado : EstadoTarea.values()) {
      if (estado.getValor() == valor) {
        return estado;
      }
    }
    throw new IllegalArgumentException("Valor de estado inválido: " + valor);
  }
}