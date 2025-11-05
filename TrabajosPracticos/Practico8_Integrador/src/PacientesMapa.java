import java.util.*;

public class PacientesMapa implements MapaPacientes {
    private static final int CAPACIDAD_INICIAL = 16;
    private static final double FACTOR_CARGA = 0.75;
    
    private List<Entry>[] tabla;
    private int size;
    
    static class Entry {
        String key;
        Paciente value;
        Entry next;
        
        Entry(String key, Paciente value) {
            this.key = key;
            this.value = value;
        }
    }
    
    @SuppressWarnings("unchecked")
    public PacientesMapa() {
        this.tabla = new List[CAPACIDAD_INICIAL];
        this.size = 0;
    }
    
    private int hash(String key) {
        return Math.abs(key.hashCode()) % tabla.length;
    }
    
    @Override
    public void put(String dni, Paciente paciente) {
        if ((double) size / tabla.length > FACTOR_CARGA) {
            rehash();
        }
        
        int index = hash(dni);
        if (tabla[index] == null) {
            tabla[index] = new ArrayList<>();
        }
        
        for (Entry entry : tabla[index]) {
            if (entry.key.equals(dni)) {
                entry.value = paciente;
                return;
            }
        }
        
        tabla[index].add(new Entry(dni, paciente));
        size++;
    }
    
    @Override
    public Paciente get(String dni) {
        int index = hash(dni);
        if (tabla[index] == null) return null;
        
        for (Entry entry : tabla[index]) {
            if (entry.key.equals(dni)) {
                return entry.value;
            }
        }
        return null;
    }
    
    @Override
    public boolean remove(String dni) {
        int index = hash(dni);
        if (tabla[index] == null) return false;
        
        Iterator<Entry> iterator = tabla[index].iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (entry.key.equals(dni)) {
                iterator.remove();
                size--;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean containsKey(String dni) {
        return get(dni) != null;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public Iterable<String> keys() {
        List<String> keys = new ArrayList<>();
        for (List<Entry> bucket : tabla) {
            if (bucket != null) {
                for (Entry entry : bucket) {
                    keys.add(entry.key);
                }
            }
        }
        return keys;
    }
    
    @SuppressWarnings("unchecked")
    private void rehash() {
        List<Entry>[] tablaVieja = tabla;
        tabla = new List[tablaVieja.length * 2];
        size = 0;
        
        for (List<Entry> bucket : tablaVieja) {
            if (bucket != null) {
                for (Entry entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }
    
    public void mostrarEstado() {
        System.out.println("Tamaño tabla: " + tabla.length + " | Load Factor: " + ((double) size / tabla.length));
        for (int i = 0; i < tabla.length; i++) {
            System.out.print("[Bucket " + i + "] -> ");
            if (tabla[i] == null || tabla[i].isEmpty()) {
                System.out.println("vacío");
            } else {
                for (int j = 0; j < tabla[i].size(); j++) {
                    Entry entry = tabla[i].get(j);
                    System.out.print("(" + entry.key + ", " + entry.value.nombre + ")");
                    if (j < tabla[i].size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }
        }
    }
}

interface MapaPacientes {
    void put(String dni, Paciente p);
    Paciente get(String dni);
    boolean remove(String dni);
    boolean containsKey(String dni);
    int size();
    Iterable<String> keys();
}