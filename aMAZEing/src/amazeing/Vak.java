package amazeing;

/**
 *
 * @author Kahoo
 */
public class Vak {
    private Figuur figuur;
    private int x;
    private int y;

    // Constructor
    public Vak(int x, int y, Figuur figuur) {
        this.x = x;
        this.y = y;
        this.figuur = figuur;
    }
    
    public boolean isBazooka(Vak vak) {
        if(vak.getFiguur() instanceof Bazooka){
            return true;
        }
        return false;
    }
    public boolean isMuur(Vak vak){
        if(vak.getFiguur() instanceof Muur) {
            return true;
        }
        return false;
    }
    public boolean isVriend(Vak vak) {
        if(vak.getFiguur() instanceof Vriend) {
            return true;
        }
        return false;
    }

    
    public String toString() {
        String x = Integer.toString(getx());
        String y = Integer.toString(gety());
        String cord = x + " " + y + " bevat " + figuur.getNaam();
        return cord;
    }
    
    // Getters and Setters
    public Figuur getFiguur(){
        return figuur;
    }
    public void setFiguur(Figuur figuur){
        this.figuur = figuur;
    }
    public int getx() {
        return x;
    }
    
    public int gety() {
        return y;
    }
}
