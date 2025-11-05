public class AVLNode {
    public Turno turno;
    public AVLNode left;
    public AVLNode right;
    public int height;
    
    public AVLNode(Turno turno) {
        this.turno = turno;
        this.height = 1;
    }
}