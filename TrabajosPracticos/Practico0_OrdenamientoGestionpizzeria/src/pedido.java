public class pedido{

    private String nombre;
    private double precio;
    private int tiempoPreparacion; //MINUTOS

    public pedido(String nombre, double precio, int tiempoPreparacion){
        this.nombre=nombre;
        this.precio=precio;
        this.tiempoPreparacion=tiempoPreparacion;
    }

    //
    public void setPrecio(double precio){
        this.precio=precio;
    }
    public void setTiempoPreparacion(int tiempoPreparacion){
        this.tiempoPreparacion=tiempoPreparacion;
    }
    public String getNombre(){
        return nombre;
    }
    public double getPrecio(){
        return precio;
    }
    public int getTiempoPreparacion(){
        return tiempoPreparacion;
    }
    //

    @Override
    public String toString(){
        return "Nombre del cliente: "+nombre +"| Precio del pedido: "+ precio +"| Tiempo de preparacion: "+ tiempoPreparacion;
    }

}
