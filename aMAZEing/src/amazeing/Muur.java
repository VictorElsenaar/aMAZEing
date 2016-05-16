package amazeing;

/**
 *
 * @author Kahoo
 */
public class Muur extends Figuur {
    private boolean borderMuur;
    
    public Muur() {
        super("muur");
    }
    
    public void setBorderMuur(boolean borderMuur) {
        this.borderMuur = borderMuur;
    }
    
    public boolean getBorderMuur() {
        return borderMuur;
    }
}
