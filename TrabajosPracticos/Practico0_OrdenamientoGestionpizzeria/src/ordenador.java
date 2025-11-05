import java.util.List;
//import java.util.Random;

public class ordenador{

    
    //Ordenar tiempo de preparacion con Metodo de ordenamiento por insercion
    public void ordenarInsercion(List<pedido>pedidos){

        if(pedidos == null || pedidos.size() <=1){
            return;
        }

        for(int i =1; i<pedidos.size(); i++){
            pedido pedidoActual = pedidos.get(i);
            
            //Movemos los elementos mayores a la derecha
            int j = i-1;
            while (j>=0 && pedidos.get(j).getTiempoPreparacion() > pedidoActual.getTiempoPreparacion()){
                pedidos.set(j+1, pedidos.get(j));
                j--;
            }
            pedidos.set(j+1, pedidoActual);
        }
    }

    //Ordenar precio con Metodo de ordenamiento Shellsort
    public void ordenarShellsort(List<pedido> pedidos) {
        if (pedidos == null || pedidos.size() <= 1) return;
        int n = pedidos.size();

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                pedido temp = pedidos.get(i);
                int j;
                for (j = i; j >= gap && pedidos.get(j - gap).getPrecio() > temp.getPrecio(); j -= gap) {
                    pedidos.set(j, pedidos.get(j - gap));
                }
                pedidos.set(j, temp);
            }
        }
    }
    //Ordenar quicksort
    public void ordenarPorNombreQuicksort(List<pedido> pedidos) {
        if (pedidos == null || pedidos.size() <= 1) return;
        quicksort(pedidos, 0, pedidos.size() - 1);
    }

    private void quicksort(List<pedido> pedidos, int low, int high) {
        if (low < high) {
            int pi = particion(pedidos, low, high);
            quicksort(pedidos, low, pi - 1);
            quicksort(pedidos, pi + 1, high);
        }
    }

    private int particion(List<pedido> pedidos, int low, int high) {
        String pivot = pedidos.get(high).getNombre();
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (pedidos.get(j).getNombre().compareToIgnoreCase(pivot) <= 0) {
                i++;
                intercambiar(pedidos, i, j);
            }
        }
        
        intercambiar(pedidos, i + 1, high);
        return i + 1;
    }

    // Método auxiliar para intercambiar elementos
    private void intercambiar(List<pedido> pedidos, int i, int j) {
        pedido temp = pedidos.get(i);
        pedidos.set(i, pedidos.get(j));
        pedidos.set(j, temp);
    }

    

}