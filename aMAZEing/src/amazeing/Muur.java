package amazeing;

import java.awt.Color;

/**
 *
 * @author Kahoo
 */
public class Muur extends Figuur {
    private boolean borderMuur;
    
    public Muur() {
        super(Color.DARK_GRAY);
        borderMuur = false;
    }
    public Muur(boolean borderMuur) {
        super(Color.BLACK);
        this.borderMuur = borderMuur;
    }
    
    public boolean getBorderMuur() {
        return borderMuur;
    }
}
