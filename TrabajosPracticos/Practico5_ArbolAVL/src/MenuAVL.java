import java.util.Scanner;

public class MenuAVL {
    public static void main(String[] args) {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);
        ArbolAVL arbol = new ArbolAVL();

        while (opcion!=5){
                
            System.out.println("Bienvenido al Sistema de Practico Arbol AVL.");
            System.out.println("1] Ejercicio 1 - Inserciones y FE paso a paso (caso LL y RR).");
            System.out.println("2] Ejercicio 2 - Inserciones con rotación doble (caso LR y RL).");
            System.out.println("3] Ejercicio 3 - Secuencia ordenada y “efecto peinar”.");
            System.out.println("4] Ejercicio 4 - Eliminación con rebalanceo.");
            System.out.println("5] Ejercicio 5 - Comprobador de AVL.");
            System.out.println("6] Ejercicio 6 - Factor de equilibrio completo.");
            System.out.println("7] Ejercicio 7 - Implementación guiada: rotación izquierda.");
            System.out.println("8] Ejercicio 8 - Implementación guiada: rotación doble izquierda-derecha (LR).");
            System.out.println("] Salir.");
            System.out.println("Ingresar una de las opciones: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: 
                    System.out.println("Inserte la secuencia: 30, 20, 10, 40, 50, 60.");
                    System.out.println("a) Muestre el estado del árbol en cada paso.");
                    System.out.println("b) Calcule alturas y factor de equilibrio (FE) de cada nodo en cada paso.");
                    System.out.println("a)c) Indique qué rotación se aplica en cada desbalance (LL o RR) y por qué.");
                    insertarYmostrar(arbol, 30, "30");
                    insertarYmostrar(arbol, 20, "20");
                    insertarYmostrar(arbol, 10, "10");
                    insertarYmostrar(arbol, 40, "40");
                    insertarYmostrar(arbol, 50, "50");
                    insertarYmostrar(arbol, 60, "60");
                    System.out.println("Esta balanceado?: "+arbol.estaBalanceado());
                    break;
                case 2:
                    System.out.println("Inserte la secuencia: 30, 10, 20, 40, 35, 37.");
                    System.out.println("a) Muestre el estado del árbol en cada paso.");
                    System.out.println("b) Identifique los desbalances (FE = ±2).");
                    System.out.println("c) Especifique cuándo corresponde rotación doble (LR o RL) y ejecútela.");
                    insertarYmostrar(arbol, 30, "30");
                    insertarYmostrar(arbol, 10, "10");
                    insertarYmostrar(arbol, 20, "20");  // aca se detecta caso LR
                    insertarYmostrar(arbol, 40, "40");
                    insertarYmostrar(arbol, 35, "35"); // aca se detecta caso RL
                    insertarYmostrar(arbol, 37, "37");
                    System.out.println("Esta balanceado?: "+arbol.estaBalanceado());
                    break;
                case 3:
                    System.out.println("Inserte la secuencia: 5, 10, 15, 20, 25, 30, 35.");
                    System.out.println("a) Explique por qué un ABB puro se desbalancea con datos crecientes.");
                    System.out.println("RTA: Se desbalancea con datos crecientes porque se ingresan los numeros crecientes comohijos derechos del nodo más a la derecha, esto genera un tipo lista enlazada quedando la estructura del arbol como O(n) lineal y no O(log n), conociendo esto como efecto peinar o arbol degenerado.");
                    System.out.println("b) Detalle las rotaciones que hacen que el AVL mantenga altura O(log n).");
                    insertarYmostrar(arbol, 5, "5");
                    insertarYmostrar(arbol, 10, "10");
                    insertarYmostrar(arbol, 15, "15");  // aca pasa la rotacion simple izquierda
                    insertarYmostrar(arbol, 20, "20");
                    insertarYmostrar(arbol, 25, "25");  // aca pasa la rotacion simple izquierda
                    insertarYmostrar(arbol, 30, "30");  // aca pasa la rotacion simple izquierda 
                    insertarYmostrar(arbol, 35, "35");
                    System.out.println("Esta balanceado?: "+arbol.estaBalanceado());
                    System.out.println("Altura del arbol: "+arbol.alturaArbol());
                    break;
                case 4:
                    System.out.println("Inserte la secuencia: 50, 30, 70, 20, 40, 60, 80, 65, 75. Elimine: 20, luego 70.");
                    System.out.println("a) Dibuje el árbol tras cada borrado.");
                    System.out.println("b) Indique FE de los nodos afectados y rotaciones necesarias para restaurar el balance.");
                    insertarYmostrar(arbol, 50, "50");
                    insertarYmostrar(arbol, 30, "30");
                    insertarYmostrar(arbol, 70, "70");  
                    insertarYmostrar(arbol, 20, "20");
                    insertarYmostrar(arbol, 40, "40");  
                    insertarYmostrar(arbol, 60, "60");   
                    insertarYmostrar(arbol, 80, "80");
                    insertarYmostrar(arbol, 65, "65");
                    insertarYmostrar(arbol, 75, "75");
                    System.out.println("=== ARBOL INICIAL ===");
                    arbol.mostrarArbol();
                    System.out.println("Esta alanceado? " + arbol.estaBalanceado());
                    
                    System.out.println("=== ELIMINAMOS 20 ===");
                    arbol.eliminar(20);
                    System.out.println("Esta alanceado? " + arbol.estaBalanceado());
                    
                    System.out.println("=== ELIMINAMOS 70 ===");
                    arbol.eliminar(70);
                    System.out.println("Esta alanceado? " + arbol.estaBalanceado());
                    
                    System.out.println("=== MOSTRAMOS ARBOL InORDER ===");
                    arbol.mostrarInOrder();
                    break;
                case 5:
                    System.out.println("Implemente un método esAVL(Nodo r).");
                    System.out.println("a) Devuelva (esAVL, altura) en una sola pasada recursiva.");
                    System.out.println("b) Verifique que para todo nodo |altura(izq) - altura(der)| ≤ 1 y que además respete la propiedad de ABB.");
                    break;
                case 6:
                    System.out.println("Inserte la secuencia: 10, 100, 20, 80, 40, 70.");
                    System.out.println("a) Liste para el árbol final (valor, altura, FE) de todos los nodos.");
                    System.out.println("b) Marque los nodos críticos donde surgieron FE = ±2 durante el proceso.");
                    insertarYmostrar(arbol, 30, "30");
                    insertarYmostrar(arbol, 10, "10");
                    insertarYmostrar(arbol, 20, "20");  
                    insertarYmostrar(arbol, 40, "40");
                    insertarYmostrar(arbol, 35, "35");
                    insertarYmostrar(arbol, 37, "37");
                    
                    System.out.println("=== REPORTE FINAL ===");
                    arbol.mostrarArbol();  // Mostramos (valor, FE, altura) de todos los nodos y como queda el arbol
                    break;
                case 7:
                    System.out.println("Complete el código de una rotación simple a izquierda y úselo en insertar.");
                    System.out.println("a) Muestre antes/después sobre un subárbol donde ocurra caso RR.");
                    System.out.println("b) Actualice correctamente las alturas involucradas.");
                    insertarYmostrar(arbol, 10, "10");
                    insertarYmostrar(arbol, 20, "20");
                    insertarYmostrar(arbol, 30, "30");  //ROTACION IZQUIERD
                    break;
                case 8:
                    System.out.println("Programe rotacionDobleIzquierdaDerecha(n) usando dos rotacionessimples.");
                    System.out.println("a) Justifique por qué LR ≡ (rotación simple izquierda en hijo) + (rotación simple derecha en n).");
                    System.out.println("b) Valide con el caso del ejercicio 2.");
                    break;
                case 10:
                    System.out.println("a) Genere 3 secuencias de 20 números (creciente, decreciente, pseudoaleatoria con repetidos) e inserte en un AVL (ignore repetidos si su diseño no los admite).");
                    System.out.println("b) Escriba tests que verifiquen tras cada inserción: esAVL == true, alturas correctas y orden in-order creciente.");
                    System.out.println("c) Informe cuántas rotaciones totales se aplicaron en cada secuencia.");
                    probarSecuencia("CRECIENTE", new int[]{1,2,3,4,5,6,7,8,9,10});
                    probarSecuencia("DECRECIENTE", new int[]{10,9,8,7,6,5,4,3,2,1});
                    probarSecuencia("ALEATORIA", new int[]{5,2,8,1,9,3,7,4,6,2}); // con repetido
                    break;
                default:
                    break;
            }   
        }
        scanner.close();   
    }

    public static void insertarYmostrar(ArbolAVL arbol,int valor,String nombre){
        //Insertamos el numero
        arbol.insertar(valor);
        System.out.println("[-----------------------------------]");
    }
    private static void probarSecuencia(String nombre, int[] valores) {
        ArbolAVL arbol = new ArbolAVL();
        
        System.out.println(nombre + ":");
        for (int valor : valores) {
            arbol.insertar(valor);
        }
        
        System.out.println("Rotaciones: " + arbol.getContadorRotaciones());
        System.out.println("Altura: " + arbol.alturaArbol());
        System.out.println("Balanceado: " + arbol.estaBalanceado());
    }
}

