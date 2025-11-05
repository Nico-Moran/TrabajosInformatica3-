public class ArbolAVL {

    private NodoAVL raiz;
    private int contadorRotaciones;

    public ArbolAVL(){
        this.raiz = null;
        this.contadorRotaciones = 0;
    }
    //Obtenemos la altura del nodo
    private int altura(NodoAVL nodo){
        return (nodo==null) ? 0 : nodo.altura;
    }
    //Obtenemos el factor de equilibrio
    private int factorEquilibrio(NodoAVL nodo){
        return (nodo==null) ? 0 : altura(nodo.derecha) - altura(nodo.izquierda);
    }
    //Actualizamos altura del nodo
    private void actualizarAltura(NodoAVL nodo){
        if(nodo != null){
            nodo.altura = 1 + Math.max(altura(nodo.izquierda),altura(nodo.derecha));
        }
    }
    /* 
    
     *                                                                                              ========== ROTACIONES ==========
     * 
    */
     //Rotacion SIMPLE a la derecha(RR)
    private NodoAVL rotacionDerecha(NodoAVL y){
        System.out.println("Rotacion a la Derecha(RR) en el nodo "+y.valor);
        contadorRotaciones++;

        NodoAVL x = y.izquierda;
        NodoAVL T2 = x.derecha;

        //Realizamos la rotacion
        x.derecha = y;
        y.izquierda = T2;

        //Actualizamos la altura
        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }
    //Rotacion SIMPLE a la izquierda(LL)
    private NodoAVL rotacionIzquierda(NodoAVL x){
        System.out.println("Rotacion a la izquierda(LL) en el nodo "+x.valor);
        contadorRotaciones++;

        NodoAVL y= x.derecha;
        NodoAVL T2 = y.izquierda;

        //Realizamos la rotacion
        y.izquierda = x;
        x.derecha = T2;

        //Actualizamos la altura
        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }
    //Rotacion Doble Izquierda Derecha(LR)
    private NodoAVL rotacionIzquierdaDerecha(NodoAVL nodo) {
        System.out.println("Realizamos la Rotacion doble LR en nodo " + nodo.valor);
        contadorRotaciones++;
        System.out.println("Primero: rotacion izquierda en hijo izquierdo " + nodo.izquierda.valor);
        nodo.izquierda = rotacionIzquierda(nodo.izquierda);
        System.out.println("Segundo: rotacion derecha en nodo " + nodo.valor);
        return rotacionDerecha(nodo);
    }
    //Rotacion Doble Derecha Izquierda(RL)
    private NodoAVL rotacionDerechaIzquierda(NodoAVL nodo) {
        System.out.println("Realizamos la Rotacion Derecha-Izquierda (RL) en nodo " + nodo.valor);
        contadorRotaciones++;
        System.out.println("Primero: rotacion derecha en hijo derecho " + nodo.derecha.valor);
        nodo.derecha = rotacionDerecha(nodo.derecha);
        System.out.println("Segundo: rotacion izquierda en nodo " + nodo.valor);
        return rotacionIzquierda(nodo);
    }
    /* 
     * 
     *                                                                                             ========== ELIMINACIÓN ==========
     * 
    */
    
    public void eliminar(int valor) {
        System.out.println("Eliminando: " + valor);
        raiz = eliminarRecursivo(raiz, valor);
        System.out.println("Eliminación completada.");
        if(!estaBalanceado()){
            System.out.println("ADVERTENCIA: El arbol NO está balanceado después de eliminar " + valor);
        }
        mostrarArbol();
    }

    private NodoAVL eliminarRecursivo(NodoAVL nodo, int valor) {
        // Paso 1: Eliminación estándar de BST
        if (nodo == null) {
            System.out.println("Valor " + valor + " no encontrado");
            return null;
        }

        if (valor < nodo.valor) {
            nodo.izquierda = eliminarRecursivo(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = eliminarRecursivo(nodo.derecha, valor);
        } else {
            // Nodo encontrado - proceder con eliminación
            System.out.println("Nodo " + valor + " encontrado, eliminando...");

            // Caso 1: Nodo hoja o con un solo hijo
            if (nodo.izquierda == null || nodo.derecha == null) {
                NodoAVL temp = (nodo.izquierda != null) ? nodo.izquierda : nodo.derecha;
                
                if (temp == null) {
                    // Caso hoja
                    System.out.println("Nodo hoja eliminado");
                    return null;
                } else {
                    // Caso un solo hijo
                    System.out.println("Nodo con un hijo eliminado");
                    return temp;
                }
            } else {
                // Caso 2: Nodo con dos hijos - encontrar sucesor inorder
                NodoAVL sucesor = encontrarMinimo(nodo.derecha);
                System.out.println("Nodo con dos hijos, reemplazando con sucesor: " + sucesor.valor);
                
                nodo.valor = sucesor.valor;
                nodo.derecha = eliminarRecursivo(nodo.derecha, sucesor.valor);
            }
        }

        // Paso 2: Actualizar altura del nodo actual
        actualizarAltura(nodo);

        // Paso 3: Obtener factor de equilibrio
        int fe = factorEquilibrio(nodo);
        System.out.println("Nodo " + nodo.valor + " - FactorEquilibrio después de eliminar: " + fe);

        // Paso 4: Rebalancear si es necesario
        return rebalancearEliminacion(nodo, fe);
    }

    private NodoAVL rebalancearEliminacion(NodoAVL nodo, int fe) {
        // Caso Left Left (LL)
        if (fe < -1 && factorEquilibrio(nodo.izquierda) <= 0) {
            System.out.println("Rotación LL necesaria en nodo " + nodo.valor);
            return rotacionDerecha(nodo);
        }
        
        // Caso Left Right (LR)
        if (fe < -1 && factorEquilibrio(nodo.izquierda) > 0) {
            System.out.println("Rotación LR necesaria en nodo " + nodo.valor);
            return rotacionIzquierdaDerecha(nodo);
        }
        
        // Caso Right Right (RR)
        if (fe > 1 && factorEquilibrio(nodo.derecha) >= 0) {
            System.out.println("Rotación RR necesaria en nodo " + nodo.valor);
            return rotacionIzquierda(nodo);
        }
        
        // Caso Right Left (RL)
        if (fe > 1 && factorEquilibrio(nodo.derecha) < 0) {
            System.out.println("Rotación RL necesaria en nodo " + nodo.valor);
            return rotacionDerechaIzquierda(nodo);
        }
        
        return nodo;
    }

    private NodoAVL encontrarMinimo(NodoAVL nodo) {
        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }
        return nodo;
    }
    /*
     * 
     *                                                                                          ========== INSERCIÓN ==========
     * 
     */
                                                                                      
    //Insertar al arbon con balanceo automatico
    public void insertar(int valor){
        System.out.println("Numero insertado: "+valor);

        raiz=insertarRecursivo(raiz,valor);
        System.out.println("El valor "+valor +" se inserto correctamente.");
        if(!estaBalanceado()){
            System.out.println("El arbol no esta balanceado por ingresar "+valor);
        }
        mostrarArbol();
    }

    private NodoAVL insertarRecursivo(NodoAVL nodo, int valor){
        //Paso 1: insertamos normal
        if(nodo == null){
            return new NodoAVL(valor);
        }
        if(valor<nodo.valor){
            nodo.izquierda = insertarRecursivo(nodo.izquierda, valor);
        }else if(valor > nodo.valor){
            nodo.derecha = insertarRecursivo(nodo.derecha, valor);
        }else{
            return nodo; //Los valores duplicados no permitidos
        }

        //Paso 2: Actualizar la altura del Nodo
        actualizarAltura(nodo);

        //Paso 3: Obtener Factor de equilibrio
        int fe = factorEquilibrio(nodo);

        //Paso 4: Verificar desbalance y aplicar rotaciones
        System.out.println("Nodo "+nodo.valor+" - FactorEquilibrio: "+fe);

        //CASO RR(derecha-derecha) - FE>1 significa derecha alta
        if(fe > 1 && valor > nodo.derecha.valor){
            return rotacionIzquierda(nodo);
        }
        //CASO LL(izquierda-izquierda) - FE<-1 significa izquierda alta  
        if(fe < -1 && valor < nodo.izquierda.valor){
            return rotacionDerecha(nodo);
        }
        //CASO RL(derecha-izquierda)
        if(fe > 1 && valor < nodo.derecha.valor){
            return rotacionDerechaIzquierda(nodo);
        }
        //CASO LR(izquierda-derecha)
        if(fe < -1 && valor > nodo.izquierda.valor){
            return rotacionIzquierdaDerecha(nodo);
        }
        return nodo;//Sin cambios si esta balanceado
    }
    
     /*
      * 
      *                                                                         ========== METODOS DE VISUALIZACION ==========

      */
     
    
    //MOSTRAR ARBOL INORDER
    public void mostrarInOrder(){
        System.out.println("Recorrido del arbol InOrder: ");
        mostrarInOrderRecursivo(raiz);
        System.out.println();
    }
    public void mostrarInOrderRecursivo(NodoAVL nodo){
        if(nodo != null){
            mostrarInOrderRecursivo(nodo.izquierda);
            System.out.println(nodo.valor + "(FE: "+factorEquilibrio(nodo)+" )");
            mostrarInOrderRecursivo(nodo.derecha);
        }
    }
    //MOSTRAR ARBOL VISUAL
    public void mostrarArbol(){
        System.out.println("Estructura del arbol: ");
        if(raiz == null){
            System.out.println("Arbol vacio.");
            return;
        }
        mostrarArbolRecursivo(raiz,0);
    }
    public void mostrarArbolRecursivo(NodoAVL nodo, int nivel){
        if(nodo == null){
            return;
        }
        mostrarArbolRecursivo(nodo.derecha, nivel+1);
        for (int i = 0; i < nivel; i++) {
        System.out.print("   ");
        }       
        //Imprimimos el nodo con su FE
        System.out.println(nodo.valor + " (FE:" + factorEquilibrio(nodo) + ", Alt:" + nodo.altura + ")");

        mostrarArbolRecursivo(nodo.izquierda, nivel+1);
    }
    //OBTENER ALTURA DEL ARBOL
    public int alturaArbol(){
        return altura(raiz);
    }
    //VERIFICAR SI ESTA BALANCEADO
    public boolean estaBalanceado(){
        return estaBalanceadoRecursivo(raiz);
    }
    private boolean estaBalanceadoRecursivo(NodoAVL nodo){
        if(nodo == null){
            return true;
        }
        int fe= factorEquilibrio(nodo);
        return (Math.abs(fe)<=1) && estaBalanceadoRecursivo(nodo.izquierda) && estaBalanceadoRecursivo(nodo.derecha);
    }
    public boolean isEmpty(){
        return raiz==null;
    }
    /*

     *                                                                                          ========== COMPROBADOR AVL ==========
     * 
     */ 
    public class ComprobadorAVL {
        public boolean esAVL;
        public int altura;
        public ComprobadorAVL(boolean esAVL, int altura) {
            this.esAVL = esAVL;
            this.altura = altura;
        }
    }

    public boolean esAVL() {
        ComprobadorAVL resultado = esAVL(raiz);
        System.out.println("Altura: " + resultado.altura + ", Es AVL: " + resultado.esAVL);
        return resultado.esAVL;
    }

    private ComprobadorAVL esAVL(NodoAVL nodo) {
        if (nodo == null) {
            return new ComprobadorAVL(true, 0);
        }

        // Verificamos subarboles
        ComprobadorAVL izq = esAVL(nodo.izquierda);
        ComprobadorAVL der = esAVL(nodo.derecha);

        // Si el subarbol no es AVL todo el arbol no lo es
        if (!izq.esAVL || !der.esAVL) {
            return new ComprobadorAVL(false, 0);
        }

        // Verificar propiedad de ABB
        if (nodo.izquierda != null && !esMenor(nodo.izquierda, nodo.valor)) {
            return new ComprobadorAVL(false, 0);
        }
        if (nodo.derecha != null && !esMayor(nodo.derecha, nodo.valor)) {
            return new ComprobadorAVL(false, 0);
        }

        // Verificar balance
        int alturaActual = 1 + Math.max(izq.altura, der.altura);
        int factorEquilibrio = Math.abs(izq.altura - der.altura);
        
        if (factorEquilibrio > 1) {
            return new ComprobadorAVL(false, 0);
        }

        return new ComprobadorAVL(true, alturaActual);
    }

    private boolean esMenor(NodoAVL nodo, int valor) {
        if (nodo == null) return true;
        return nodo.valor < valor && 
               esMenor(nodo.izquierda, valor) && 
               esMenor(nodo.derecha, valor);
    }

    private boolean esMayor(NodoAVL nodo, int valor) {
        if (nodo == null) return true;
        return nodo.valor > valor && 
               esMayor(nodo.izquierda, valor) && 
               esMayor(nodo.derecha, valor);
    }


    public int getContadorRotaciones() {
        return contadorRotaciones;
    }
    public void resetearContador() {
        contadorRotaciones = 0;
    }
}