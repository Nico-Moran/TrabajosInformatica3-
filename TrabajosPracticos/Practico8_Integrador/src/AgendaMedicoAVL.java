import java.time.LocalDateTime;
import java.util.*;

public class AgendaMedicoAVL implements AgendaMedico {
    protected AVLNode root;
    private Map<String, AVLNode> turnoMap;
    
    public AgendaMedicoAVL() {
        this.turnoMap = new HashMap<>();
    }
    
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }
    
    private int balanceFactor(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }
    
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        
        x.right = y;
        y.left = T2;
        
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        
        return x;
    }
    
    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        
        y.left = x;
        x.right = T2;
        
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        
        return y;
    }
    
    @Override
    public boolean agendar(Turno turno) {
        if (tieneSuperposicion(turno)) {
            return false;
        }
        
        root = insert(root, turno);
        AVLNode nuevoNodo = buscarNodo(root, turno.fechaHora);
        if (nuevoNodo != null) {
            turnoMap.put(turno.id, nuevoNodo);
        }
        return true;
    }
    
    private AVLNode insert(AVLNode node, Turno turno) {
        if (node == null) {
            return new AVLNode(turno);
        }
        
        if (turno.fechaHora.isBefore(node.turno.fechaHora)) {
            node.left = insert(node.left, turno);
        } else if (turno.fechaHora.isAfter(node.turno.fechaHora)) {
            node.right = insert(node.right, turno);
        } else {
            return node;
        }
        
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balanceFactor(node);
        
        if (balance > 1 && turno.fechaHora.isBefore(node.left.turno.fechaHora)) {
            return rotateRight(node);
        }
        if (balance < -1 && turno.fechaHora.isAfter(node.right.turno.fechaHora)) {
            return rotateLeft(node);
        }
        if (balance > 1 && turno.fechaHora.isAfter(node.left.turno.fechaHora)) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && turno.fechaHora.isBefore(node.right.turno.fechaHora)) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        
        return node;
    }
    
    private boolean tieneSuperposicion(Turno nuevoTurno) {
        Turno anterior = findMaxBefore(root, nuevoTurno.fechaHora);
        Turno siguiente = findMinAfter(root, nuevoTurno.fechaHora);
        
        if (anterior != null && nuevoTurno.seSuperponeCon(anterior)) {
            return true;
        }
        if (siguiente != null && nuevoTurno.seSuperponeCon(siguiente)) {
            return true;
        }
        
        return false;
    }
    
    private Turno findMaxBefore(AVLNode node, LocalDateTime fecha) {
        if (node == null) return null;
        
        if (node.turno.fechaHora.isBefore(fecha)) {
            Turno right = findMaxBefore(node.right, fecha);
            return right != null ? right : node.turno;
        } else {
            return findMaxBefore(node.left, fecha);
        }
    }
    
    private Turno findMinAfter(AVLNode node, LocalDateTime fecha) {
        if (node == null) return null;
        
        if (node.turno.fechaHora.isAfter(fecha)) {
            Turno left = findMinAfter(node.left, fecha);
            return left != null ? left : node.turno;
        } else {
            return findMinAfter(node.right, fecha);
        }
    }
    
    @Override
    public boolean cancelar(String idTurno) {
        AVLNode node = turnoMap.get(idTurno);
        if (node == null) return false;
        
        root = delete(root, node.turno.fechaHora);
        turnoMap.remove(idTurno);
        return true;
    }
    
    private AVLNode delete(AVLNode root, LocalDateTime fecha) {
        if (root == null) return root;
        
        if (fecha.isBefore(root.turno.fechaHora)) {
            root.left = delete(root.left, fecha);
        } else if (fecha.isAfter(root.turno.fechaHora)) {
            root.right = delete(root.right, fecha);
        } else {
            if ((root.left == null) || (root.right == null)) {
                AVLNode temp = (root.left != null) ? root.left : root.right;
                root = temp;
            } else {
                AVLNode temp = minValueNode(root.right);
                root.turno = temp.turno;
                root.right = delete(root.right, temp.turno.fechaHora);
            }
        }
        
        if (root == null) return root;
        
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = balanceFactor(root);
        
        if (balance > 1 && balanceFactor(root.left) >= 0) {
            return rotateRight(root);
        }
        if (balance > 1 && balanceFactor(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        if (balance < -1 && balanceFactor(root.right) <= 0) {
            return rotateLeft(root);
        }
        if (balance < -1 && balanceFactor(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        
        return root;
    }
    
    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    private AVLNode buscarNodo(AVLNode node, LocalDateTime fecha) {
        if (node == null) return null;
        
        if (fecha.isEqual(node.turno.fechaHora)) {
            return node;
        } else if (fecha.isBefore(node.turno.fechaHora)) {
            return buscarNodo(node.left, fecha);
        } else {
            return buscarNodo(node.right, fecha);
        }
    }
    
    @Override
    public Optional<Turno> siguiente(LocalDateTime t) {
        AVLNode node = findCeiling(root, t);
        return node != null ? Optional.of(node.turno) : Optional.empty();
    }
    
    private AVLNode findCeiling(AVLNode node, LocalDateTime fecha) {
        if (node == null) return null;
        
        if (node.turno.fechaHora.isEqual(fecha) || node.turno.fechaHora.isAfter(fecha)) {
            AVLNode left = findCeiling(node.left, fecha);
            return left != null ? left : node;
        } else {
            return findCeiling(node.right, fecha);
        }
    }
    
    public Optional<LocalDateTime> primerHueco(LocalDateTime t0, int durMin) {
        LocalDateTime current = t0;
        AVLNode node = findCeiling(root, current);
        
        while (node != null) {
            LocalDateTime finTurnoActual = node.turno.getFin();
            LocalDateTime inicioSiguiente = node.turno.fechaHora;
            
            if (current.plusMinutes(durMin).isBefore(inicioSiguiente) || 
                current.plusMinutes(durMin).isEqual(inicioSiguiente)) {
                return Optional.of(current);
            }
            
            current = finTurnoActual;
            node = findCeiling(root, current);
        }
        
        return Optional.of(current);
    }
    
    public List<Turno> getTurnosOrdenados() {
        List<Turno> turnos = new ArrayList<>();
        inOrder(root, turnos);
        return turnos;
    }
    
    private void inOrder(AVLNode node, List<Turno> turnos) {
        if (node != null) {
            inOrder(node.left, turnos);
            turnos.add(node.turno);
            inOrder(node.right, turnos);
        }
    }
}