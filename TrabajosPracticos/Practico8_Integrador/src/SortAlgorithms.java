import java.util.*;

public class SortAlgorithms {
    
    public static void insertionSort(List<Turno> turnos, Comparator<Turno> comparator) {
        for (int i = 1; i < turnos.size(); i++) {
            Turno key = turnos.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(turnos.get(j), key) > 0) {
                turnos.set(j + 1, turnos.get(j));
                j--;
            }
            turnos.set(j + 1, key);
        }
    }
    
    public static void shellSort(List<Turno> turnos, Comparator<Turno> comparator) {
        int n = turnos.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Turno temp = turnos.get(i);
                int j;
                for (j = i; j >= gap && comparator.compare(turnos.get(j - gap), temp) > 0; j -= gap) {
                    turnos.set(j, turnos.get(j - gap));
                }
                turnos.set(j, temp);
            }
        }
    }
    
    public static void quickSort(List<Turno> turnos, Comparator<Turno> comparator) {
        quickSort(turnos, 0, turnos.size() - 1, comparator);
    }
    
    private static void quickSort(List<Turno> turnos, int low, int high, Comparator<Turno> comparator) {
        if (low < high) {
            int pi = partition(turnos, low, high, comparator);
            quickSort(turnos, low, pi - 1, comparator);
            quickSort(turnos, pi + 1, high, comparator);
        }
    }
    
    private static int partition(List<Turno> turnos, int low, int high, Comparator<Turno> comparator) {
        Turno pivot = turnos.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(turnos.get(j), pivot) <= 0) {
                i++;
                swap(turnos, i, j);
            }
        }
        swap(turnos, i + 1, high);
        return i + 1;
    }
    
    private static void swap(List<Turno> turnos, int i, int j) {
        Turno temp = turnos.get(i);
        turnos.set(i, turnos.get(j));
        turnos.set(j, temp);
    }
    
    public static Comparator<Turno> comparadorPorHora() {
        return (t1, t2) -> t1.fechaHora.compareTo(t2.fechaHora);
    }
    
    public static Comparator<Turno> comparadorPorDuracion() {
        return (t1, t2) -> Integer.compare(t1.duracionMin, t2.duracionMin);
    }
    
    public static Comparator<Turno> comparadorPorApellido(Map<String, Paciente> pacientes) {
        return (t1, t2) -> {
            Paciente p1 = pacientes.get(t1.dniPaciente);
            Paciente p2 = pacientes.get(t2.dniPaciente);
            if (p1 == null || p2 == null) return 0;
            return extraerApellido(p1.nombre).compareTo(extraerApellido(p2.nombre));
        };
    }
    
    private static String extraerApellido(String nombreCompleto) {
        String[] partes = nombreCompleto.split(" ");
        return partes[partes.length - 1];
    }
}