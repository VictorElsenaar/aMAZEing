package amazeing;

/**
 *
 * @author Kahoo
 */
public class Vak {
    private int x_as;
    private int y_as;
    public Vak() {
        
    }
    public Vak(int x_as, int y_as) {
        this.x_as = x_as;
        this.y_as = y_as;
    }
    
    public int getXAs() {
        return x_as;
    }
    
    public int getYAs() {
        return y_as;
    }
    
    public String getCord() {
        String x = Integer.toString(getXAs());
        String y = Integer.toString(getYAs());
        String cord = "Cord: " + x + ", " + y;
        return cord;
    }
}
