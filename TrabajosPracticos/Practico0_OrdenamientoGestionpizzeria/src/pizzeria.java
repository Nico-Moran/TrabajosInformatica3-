import java.util.ArrayList;
import java.util.List;


public class pizzeria {
    
    //Lista con el constructor
    private List<pedido> listaPedidos;
    public pizzeria() {
        this.listaPedidos = new ArrayList<>();
    }

    //Agregar pedido
    public void agregarPedido(pedido pedido){

        listaPedidos.add(pedido);
        System.out.println("Se agrego el pedido correctamente.");

    }

    //Eliminar pedido
    public void eliminarPedido(int indice){

        if(indice >=0 && indice < listaPedidos.size()){
            pedido eliminado = listaPedidos.remove(indice);
            System.out.println("Se elimino el pedido "+eliminado.getNombre()+" con el indice :"+indice);
        }
    }
    //Obtener pedido
    public pedido getPedido(int indice){
        if(indice >= 0 && indice < listaPedidos.size()){
            return listaPedidos.get(indice);
        }
        else{
            System.out.println("El indice ingresado no se encontro.");
            return null;
        }
    }
    //Actualizar precio producto
    public void actualizarPrecio(int indice, double precioActualizado){
        pedido pedido = getPedido(indice);
        if(pedido != null){
            pedido.setPrecio(precioActualizado);
            System.out.println("El preio del pedido se actualizo correctamente.");
        }
        else{
            System.out.println("El indice ingresado no se encontro.");
        }
    }
    //Actualizar tiempo producto
    public void actualizarTiempo(int indice, int tiempoActualizado){
        pedido pedido = getPedido(indice);
        if(pedido != null){
            pedido.setTiempoPreparacion(tiempoActualizado);
            System.out.println("Se actualizo el tiempo del producto correctamente.");
        }
        else{
            System.out.println("El indice ingresado no se encontro.");
        }
    }
    //Mostrar todos los pedidos -usuario-
    public void mostrarPedidos(){
        if(listaPedidos.isEmpty()){
            System.out.println("No hay ningun pedido ingresado.");
            return;
        }
        else{
            System.out.println("---LISTA DE LOS PEDIDOS---");
            for(int i = 0; i<listaPedidos.size();i++){
                System.out.println(i+"] "+listaPedidos.get(i));
            }
        }
    }

    
    //Obtener todos los pedidos
    public List<pedido>getListaPedidos(){
        return listaPedidos;
    }
    //Obtener cantidad de pedidos
    public int getCantidadPedidos(){
        return listaPedidos.size();
    }



}
