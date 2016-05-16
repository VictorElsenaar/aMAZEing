package amazeing;

import java.awt.Color;

/**
 *
 * @author Kahoo
 */
public class Muur extends Figuur {
    private boolean borderMuur;
    
    public Muur() {
        super("muur", Color.BLACK);
        
    }
    
    public void setBorderMuur(boolean borderMuur) {
        this.borderMuur = borderMuur;
    }
    
    public boolean getBorderMuur() {
        return borderMuur;
    }
}
