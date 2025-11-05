public class RBNode<K extends Comparable<K>, V> {
    public enum Color { RED, BLACK }
    
    public K key;
    public V value;
    public Color color;
    public RBNode<K, V> left;
    public RBNode<K, V> right;
    public RBNode<K, V> parent;
    
    // Constructor para nodos normales
    public RBNode(K key, V value, Color color) {
        this.key = key;
        this.value = value;
        this.color = color;
    }
    
    // Constructor para NIL
    public RBNode() {
        this.color = Color.BLACK;
    }
    @Override
    public String toString() {
        if (key == null) {
            return "NIL(BLACK)";
        }
        return key + "(" + color + ")";
    }
}