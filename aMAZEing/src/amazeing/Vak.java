package amazeing;

/**
 *
 * @author Kahoo
 */
public class Vak {
    private Figuur figuur;
    private int x;
    private int y;

    public Vak(int x, int y, Figuur figuur) {
        this.x = x;
        this.y = y;
        this.figuur = figuur;
    }
    
    public int getx() {
        return x;
    }
    
    public int gety() {
        return y;
    }

    public String toString() {
        String x = Integer.toString(getx());
        String y = Integer.toString(gety());
        String cord = x + " " + y + " bevat " + figuur.getNaam();
        return cord;
    }
    
    public Figuur getFiguur(){
        return figuur;
    }
    
    public void setFiguur(Figuur figuur){
        this.figuur = figuur;
    }
    
}
