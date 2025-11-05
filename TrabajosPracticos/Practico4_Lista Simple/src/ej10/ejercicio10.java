/* Ejercicio 10 – Aplicación práctica (registro de alumnos)
Crea una lista enlazada que almacene alumnos con nombre y legajo.
● Métodos: agregarAlumno(nombre, legajo), buscarAlumno(legajo),
eliminarAlumno(legajo).
● Simula un registro de tres alumnos y prueba las operaciones.
*/
public class ejercicio10 {
    public static void main(String[] args) {
        listaEnlazadaAlumnos lista = new listaEnlazadaAlumnos();

        // Agregar alumnos
        lista.agregarAlumno("Juan Perez", 1001);
        lista.agregarAlumno("Maria Gomez", 1002);
        lista.agregarAlumno("Luis Rodriguez", 1003);

        // Buscar alumnos
        System.out.println("Buscando alumno con legajo 1002: " + (buscarAlumno(lista, 1002) ? "Encontrado" : "No encontrado"));
        System.out.println("Buscando alumno con legajo 1004: " + (buscarAlumno(lista, 1004) ? "Encontrado" : "No encontrado"));

        // Eliminar un alumno
        System.out.println("Eliminando alumno con legajo 1001.");
        lista.eliminarAlumno(1001);

        // Verificar eliminación
        System.out.println("Buscando alumno con legajo 1001: " + (buscarAlumno(lista, 1001) ? "Encontrado" : "No encontrado"));
    }

    public static boolean buscarAlumno(listaEnlazadaAlumnos lista, int legajo) {
        NodoAlumno actual = lista.getCabeza(); // Usar el getter en lugar de acceso directo;
        while (actual != null) {
            if (actual.legajo == legajo) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
}