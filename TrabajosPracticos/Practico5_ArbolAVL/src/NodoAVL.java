public class NodoAVL {
    int valor;
    int altura;
    NodoAVL izquierda;
    NodoAVL derecha;
    
    public NodoAVL(int valor){
        this.valor=valor;
        this.altura=1;//El nuevo nodo tiene que tener altura 1
        this.izquierda=null;
        this.derecha=null;
    }
    
    @Override
    public String toString(){
        return String.valueOf(valor);
    }
}
