public class RBTree<K extends Comparable<K>, V> {
    public final RBNode<K, V> NIL = new RBNode<>();
    private RBNode<K, V> root = NIL;
    
    public RBTree() {
        //Punto 1
        NIL.left = NIL;
        NIL.right = NIL;
        NIL.parent = NIL;
    }
    public RBNode<K, V> getRoot() {
        return root;
    }
    /*
     *                                                                                        ====ROTACIONES====
     */
    //Rotacion izquierda
    public void rotateLeft(RBNode<K, V> x) {
        RBNode<K, V> y = x.right;
        x.right = y.left;
        if (y.left != NIL) y.left.parent = x;
        
        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        
        y.left = x;
        x.parent = y;
    }
    
    //Rotacion derecha
    public void rotateRight(RBNode<K, V> y) {
        RBNode<K, V> x = y.left;
        y.left = x.right;
        if (x.right != NIL) x.right.parent = y;
        
        x.parent = y.parent;
        if (y.parent == NIL) root = x;
        else if (y == y.parent.left) y.parent.left = x;
        else y.parent.right = x;
        
        x.right = y;
        y.parent = x;
    }
    /*
     * 
     *                                                                                     ====INGRESO====
     */
    public RBNode<K, V> IngresarBST(K key, V value) {
        RBNode<K, V> nuevo = new RBNode<>(key, value, RBNode.Color.RED);
        nuevo.left = NIL;
        nuevo.right = NIL;
        nuevo.parent = NIL;
        
        if (root == NIL) {
            root = nuevo;
        } else {
            IngresarBSTRecursivo(root, nuevo);
        }
        return nuevo;
    }
    
    private void IngresarBSTRecursivo(RBNode<K, V> actual, RBNode<K, V> nuevo) {
        if (nuevo.key.compareTo(actual.key) < 0) {
            if (actual.left == NIL) {
                actual.left = nuevo;
                nuevo.parent = actual;
            } else {
                IngresarBSTRecursivo(actual.left, nuevo);
            }
        } else {
            if (actual.right == NIL) {
                actual.right = nuevo;
                nuevo.parent = actual;
            } else {
                IngresarBSTRecursivo(actual.right, nuevo);
            }
        }
    }
    
    /*
     *                                                                                   ====BUSCAR NODO====
     */
    public RBNode<K, V> Buscar(K key) {
        return Buscar(root, key);
    }
    
    private RBNode<K, V> Buscar(RBNode<K, V> nodo, K key) {
        if (nodo == NIL) return NIL;
        
        int auxiliar = key.compareTo(nodo.key);
        if (auxiliar == 0) return nodo;
        return auxiliar < 0 ? Buscar(nodo.left, key) : Buscar(nodo.right, key);
    }
    
    /*
     *                                                                                     ====IMPRIMIR====
     */
    public void print() {
        print(root, 0);
    }
    
    private void print(RBNode<K, V> nodo, int nivel) {
        if (nodo == NIL) return;
        print(nodo.right, nivel + 1);
        for (int i = 0; i < nivel; i++){
            System.out.print("   ");
        }
        System.out.println(nodo.key + "(" + nodo.color + ")");
        print(nodo.left, nivel + 1);
    }
     /*
     *                                                                                     ====CLASIFICADOR====
     */
    public enum Caso { TIO_ROJO, LL, RR, LR, RL }//Clasificador

    public Caso clasificar(RBNode<K, V> z) {
        RBNode<K, V> p = z.parent;  // Padre
        
        // Si no hay padre o el padre es negro
        if (p == NIL || p.color == RBNode.Color.BLACK) {
            return null; // No se fix si no hay pad
        }
        
        RBNode<K, V> g = p.parent;  // Abuelo
        if (g == NIL) {
            return null;  // No se fix si no hay aube
        }
        
        // Determinar too
        RBNode<K, V> tio = (p == g.left) ? g.right : g.left;
        
        // Si el tio es rojo
        if (tio.color == RBNode.Color.RED) {
            return Caso.TIO_ROJO;
        }
        
        // casos si elTio es negro
        if (p == g.left) {
            // Padre es hijo izquierdo del abuelo
            if (z == p.left) {
                return Caso.LL;  // Lineal izquierda
            } else {
                return Caso.LR;  // Quebrada izquierda-derecha
            }
        } else {
            // Padre es hijo derecho del abuelo  
            if (z == p.right) {
                return Caso.RR;  // Lineal derecha
            } else {
                return Caso.RL;  // Quebrada derecha-izquierda
            }
        }
    }
    /*
     *                                                                                     ====RECOLOREO TIO ROJO====
     */
    public void recolorearTioRojo(RBNode<K, V> z) {
        RBNode<K, V> p = z.parent;
        RBNode<K, V> g = p.parent;
        RBNode<K, V> tio = (p == g.left) ? g.right : g.left;
        
        // Pintamos a padre y tio de negro
        p.color = RBNode.Color.BLACK;
        tio.color = RBNode.Color.BLACK;
        
        // pintamos al abue de rojo
        g.color = RBNode.Color.RED;
        
    }
    
    // Metodo auxiliar para Verificar asegurar raíz negra
    public void asegurarRaizNegra() {
        if (root.color == RBNode.Color.RED) {
            root.color = RBNode.Color.BLACK;
        }
    }
    /*
     *                                                                                     ====FixRamaIzquierd====
     */
    public void fixRamaIzquierda(RBNode<K, V> z) {
        RBNode<K, V> p = z.parent;
        RBNode<K, V> g = p.parent;
        
        if (z == p.right) {
            rotateLeft(p);
            p = z;
        }
        
        rotateRight(g);
        p.color = RBNode.Color.BLACK;
        g.color = RBNode.Color.RED;
    }
    public RBNode<K, V> sucesor(RBNode<K, V> x) {
        // Caso 1: Si tiene hijo derecho, el sucesor es el mínimo del subárbol derecho
        if (x.right != NIL) {
            return nodoMinimo(x.right);
        }
        
        // Caso 2: Si no tiene hijo derecho, buscar el primer ancestro cuyo hijo izquierdo sea x
        RBNode<K, V> y = x.parent;
        while (y != NIL && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }
    /*
     *                                                                                     ====PREDECESOR====
     */
    public RBNode<K, V> predecesor(RBNode<K, V> x) {
        // Si tiene hijo izquierdo, el predecesor es el maximo del subarbol izquierdo
        if (x.left != NIL) {
            return nodoMaximo(x.left);
        }
        
        //Si no tiene hijo izquierdo, buscar el primer ancestro cuyo hijo derecho sea x
        RBNode<K, V> y = x.parent;
        while (y != NIL && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }
    
    // Metod aux
    private RBNode<K, V> nodoMinimo(RBNode<K, V> n) {
        while (n.left != NIL) {
            n = n.left;
        }
        return n;
    }
    
    private RBNode<K, V> nodoMaximo(RBNode<K, V> n) {
        while (n.right != NIL) {
            n = n.right;
        }
        return n;
    }
     /*
     *                                                                                     ====CONSULTAR RANGO====
     */
    public java.util.List<K> rango(K a, K b) {
        java.util.List<K> resultado = new java.util.ArrayList<>();
        rangoRecursivo(root, a, b, resultado);
        return resultado;
    }
    
    private void rangoRecursivo(RBNode<K, V> nodo, K a, K b, java.util.List<K> resultado) {
        if (nodo == NIL) return;
        
        // Si nodo.key < a, solo buscar en la derecha (valores mayores)
        if (nodo.key.compareTo(a) < 0) {
            rangoRecursivo(nodo.right, a, b, resultado);
        }
        // Si nodo.key > b, solo buscar en la izquierda (valores menores)  
        else if (nodo.key.compareTo(b) > 0) {
            rangoRecursivo(nodo.left, a, b, resultado);
        }
        // Si a <= nodo.key <= b, agregar y buscar en ambos lados
        else {
            rangoRecursivo(nodo.left, a, b, resultado);
            resultado.add(nodo.key);
            rangoRecursivo(nodo.right, a, b, resultado);
        }
    }
    /*
     *                                                                                     ====VERIFICADORES====
     */
    public boolean raizNegra() {
        return root.color == RBNode.Color.BLACK;
    }
    
    public boolean sinRojoRojo() {
        return verificarRojoRojo(root);
    }
    
    private boolean verificarRojoRojo(RBNode<K, V> nodo) {
        if (nodo == NIL) return true;
        
        // Si el nodo es rojo, ambos hijos negros
        if (nodo.color == RBNode.Color.RED) {
            if (nodo.left.color == RBNode.Color.RED || nodo.right.color == RBNode.Color.RED) {
                return false;
            }
        }
        return verificarRojoRojo(nodo.left) && verificarRojoRojo(nodo.right);
    }
    //Verificar altura
    public int alturaNegra() {
        return calcularAlturaNegra(root);
    }
    
    private int calcularAlturaNegra(RBNode<K, V> nodo) {
        if (nodo == NIL) return 0;  // NIL es  colr negro
        
        int alturaIzq = calcularAlturaNegra(nodo.left);
        int alturaDer = calcularAlturaNegra(nodo.right);
        
        // Si alguna rama tiene altura inconsistente, propagar -1
        if (alturaIzq == -1 || alturaDer == -1 || alturaIzq != alturaDer) {
            return -1;
        }
        
        // sumar cada nodo neg +1
        return alturaIzq + (nodo.color == RBNode.Color.BLACK ? 1 : 0);
    }

}