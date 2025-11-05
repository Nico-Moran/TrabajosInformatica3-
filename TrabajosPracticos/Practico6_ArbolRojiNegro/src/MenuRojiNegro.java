import java.util.Scanner;

public class MenuRojiNegro {
    public static void main(String[] args) {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);
        RBTree<Integer, String> tree = new RBTree<>();

        while (opcion!=5){
                
            System.out.println("Bienvenido al Sistema de Practico Arbol RojiNegro.");
            System.out.println("1] Ejercicio 1 - Nodo y NIL sentinel (No hay nodo).");
            System.out.println("2] Ejercicio 2 - Rotación izquierda.");
            System.out.println("3] Ejercicio 3 - Rotación derecha.");
            System.out.println("4] Ejercicio 4 - Inserción como ABB (sin balance).");
            System.out.println("5] Ejercicio 5 - Clasificador de caso para fixInsert.");
            System.out.println("6] Ejercicio 6 - Recoloreo por tío rojo.");
            System.out.println("7] Ejercicio 7 - Rotación simple vs doble (un lado).");
            System.out.println("8] Ejercicio 8 - successor y predecessor.");
            System.out.println("9] Ejercicio 9 - Consulta por rango [a,b].");
            System.out.println("10] Ejercicio 10 - sVerificadores de invariantes.");
            System.out.println("Ingresar una de las opciones: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: 
                    System.out.println("Definí RBNode y un NIL negro único; inicializá root = NIL y que todos los punteros vacíos apunten a NIL.");
                    System.out.println("Objetivo: definir la estructura mínima con NIL compartido (negro).");
                    System.out.println("Tarea: crear RBNode<K,V> con key,val,color,left,right,parent y en RBTree un final RBNode<K,V> NIL negro; root = NIL.");
                    System.out.println("Entrega: clase con constructor que setee hijos/padre a NIL.");

                    // Verificacion inicial
                    System.out.println("1. Inicializamos:");
                    System.out.println("Raiz es NIL: " + (tree.getRoot()==tree.NIL));
                    System.out.println("El color de NIl es: " + tree.NIL.color);

                    // Ingresamos 4valores para testear
                    System.out.println("2. Ingreso:");
                    System.out.println("Ingresamos 10,5,15,3");
                    tree.IngresarBST(10, "Diez");
                    tree.IngresarBST(5, "Cinco");
                    tree.IngresarBST(15, "Quince");
                    tree.IngresarBST(3, "Tres");

                    System.out.println("3. Estado:");
                    tree.print();
                break;
                case 2:
                    System.out.println("Implementá rotateLeft(x) actualizando parent/left/right y la raíz si corresponde; probalo en un árbol chico.");
                    System.out.println("Objetivo: implementar rotateLeft(x).");
                    System.out.println("Tarea: dado un árbol pequeño, ejecutar la rotación y actualizar punteros (padres/hijos) correctamente.");
                    tree.IngresarBST(10, "A");
                    tree.IngresarBST(5, "B"); 
                    tree.IngresarBST(15, "C");
                    tree.IngresarBST(20, "D"); // Probar la rotacion

                    System.out.println("Antes de la rotacion esta: ");
                    tree.print();

                    RBNode<Integer,String> nodoA= tree.Buscar(15);
                    tree.rotateLeft(nodoA);

                    System.out.println("Despues de la rotacion: ");
                    tree.print();
                break;
                case 3:
                    System.out.println("Implementá rotateRight(y) (simétrico); verificá punteros y posible cambio de raíz tras la rotación.");
                    System.out.println("Objetivo: implementar rotateRight(y) (simétrico).");
                    tree.IngresarBST(20, "A");
                    tree.IngresarBST(15, "B"); 
                    tree.IngresarBST(25, "C");
                    tree.IngresarBST(10, "D"); // Probar la rotacion

                    System.out.println("Antes de la rotacion esta: ");
                    tree.print();

                    RBNode<Integer, String> nodoB= tree.Buscar(15);
                    tree.rotateRight(nodoB);
                    System.out.println("Despues de la rotacion: ");
                    tree.print();
                break;
                case 4:
                    System.out.println("Insertá como BST y devolvé el nodo rojo creado con left/right/parent = NIL; sin balance ni recoloreo.");
                    System.out.println("Objetivo: insertar la clave en orden BST y devolver el nodo nuevo.");
                    System.out.println("Tarea: insertBST(K key, V val) que cree nodo rojo con hijos/padre apuntando a NIL[-]Nota: no arreglar colores acá; se usa luego por fixInsert.");
                    tree.IngresarBST(50, "A");
                    tree.IngresarBST(30, "B");
                    tree.IngresarBST(70, "C");
                    tree.IngresarBST(20, "D");
                    tree.IngresarBST(40, "E");
                    
                    System.out.println("Arbol despues del ingreso: ");
                    tree.print();
                break;
                case 5:
                    System.out.println("Dado z,p,g, clasificá: TÍO_ROJO, LL, RR, LR o RL para decidir recoloreo o rotación.");
                    System.out.println("Objetivo: decidir qué hacer tras una inserción. [-]Tarea: función Caso clasificar(z) que devuelva: TIO_ROJO, LL, RR, LR, RL.");
                    testCaso("LL?", 50, 30, 20);
                    testCaso("RR?", 30, 50, 70);  
                    testCaso("LR?", 50, 30, 40);
                    testCaso("RL?", 30, 50, 40);
                    testCaso("TÍO_ROJO?", 50, 70, 30, 20);
                    System.out.println("[--]");
                break;
                case 6:
                    System.out.println("Tío rojo: padre y tío → negros, abuelo → rojo; continuá desde el abuelo. Asegurá raíz negra al final.");
                    System.out.println("Objetivo: implementar el caso fácil de fixInsert Tarea: si p y tío son rojos: poner negros a p y tío, rojo al abuelo g, y “subir” z = g.");
                    
                    tree.IngresarBST(50, "Abuelo");
                    tree.IngresarBST(70, "Tio");
                    tree.IngresarBST(30, "Padre");
                    RBNode<Integer, String> z = tree.IngresarBST(20, "Z");
                    
                    System.out.println("Antes del coloreo:");
                    tree.print();
                    mostrarColores(tree);
                    
                    // Aplicamos el recoloreo
                    tree.recolorearTioRojo(z);
                    tree.asegurarRaizNegra();
                    
                    System.out.println("DespueS del recoloreo:");
                    tree.print();
                    mostrarColores(tree);
                break;
                case 7:
                    System.out.println("Rama izquierda del fix: LR → rotateLeft(p) y luego rotateRight(g); LL → solo rotateRight(g) + recoloreo.");
                    System.out.println("Objetivo: cubrir un lado completo de fixInsert.");
                    RBTree<Integer, String> treeFix = new RBTree<>();
                    treeFix.IngresarBST(50, "G");
                    treeFix.IngresarBST(30, "P");
                    RBNode<Integer, String> zLR = treeFix.IngresarBST(40, "Z");

                    System.out.println("Antes:");
                    treeFix.print();

                    treeFix.fixRamaIzquierda(zLR);

                    System.out.println("Despues:");
                    treeFix.print();
                break;
                case 8:
                    System.out.println("Implementá successor y predecessor de un nodo BST; probá con claves {5,10,15}.");
                    System.out.println("Objetivo: obtener siguiente/anterior en orden.");
                    RBNode<Integer, String> n5 = tree.IngresarBST(5, "C");
                    RBNode<Integer, String> n10 = tree.IngresarBST(10, "D");
                    RBNode<Integer, String> n15 = tree.IngresarBST(15, "Q");
        
                    
                    System.out.println("Sucesor 5: " + tree.sucesor(n5).key);
                    System.out.println("Predecesor 15: " + tree.predecesor(n15).key);
                    break;
                case 9:
                    System.out.println("Hacé un in-order acotado para devolver claves en el rango [a,b] en orden, evitando visitar fuera del intervalo.");
                    System.out.println("Objetivo: devolver claves en orden con a ≤ key ≤ b.");
                    // Insertar algunos valores
                    tree.IngresarBST(5, "A");
                    tree.IngresarBST(3, "B");
                    tree.IngresarBST(7, "C");
                    tree.IngresarBST(2, "D");
                    tree.IngresarBST(4, "E");
                    tree.IngresarBST(6, "F");
                    tree.IngresarBST(8, "G");
                    
                    // Probar rango [4, 7]
                    System.out.println("Rango [4,7]: " + tree.rango(4, 7));
                break;
                case 10:
                    System.out.println("Escribí verificadores: raizNegra, sinRojoRojo y alturaNegra; usalos en tests tras varias inserciones.");
                    System.out.println("Objetivo: chequear que el árbol siga siendo RBT.");
                    // Insertar algunos valores
                    tree.IngresarBST(10, "A");
                    tree.IngresarBST(5, "B");
                    tree.IngresarBST(15, "C");
                    tree.IngresarBST(8, "D");
                  
                    
                    // Probar verificadores
                    System.out.println("Raíz negra: " + tree.raizNegra());
                    System.out.println("Sin rojo-rojo: " + tree.sinRojoRojo());
                    System.out.println("Altura negra: " + tree.alturaNegra());
                break;
                default:
                    break;
            }   
        }
        scanner.close();   
    }
     private static void testCaso(String nombre, int... valores) {
        RBTree<Integer, String> tree = new RBTree<>();
        for (int val : valores) tree.IngresarBST(val, "X");
        
        RBNode<Integer, String> z = tree.Buscar(valores[valores.length-1]);
        
        // Mostrar los números usados
        System.out.print(nombre + " [");
        for (int i = 0; i < valores.length; i++) {
            System.out.print(valores[i]);
            if (i < valores.length - 1) System.out.print(", ");
        }
        System.out.println("]: " + tree.clasificar(z));
    }
    private static void mostrarColores(RBTree<Integer, String> tree) {
        System.out.println("Colores:");
        System.out.println("Raíz 50: " + tree.Buscar(50).color);
        System.out.println("Padre 30: " + tree.Buscar(30).color);
        System.out.println("Tío 70: " + tree.Buscar(70).color);
        System.out.println("Z 20: " + tree.Buscar(20).color);
    }
}

