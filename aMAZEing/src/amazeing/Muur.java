package amazeing;

import java.awt.Color;

/**
 *
 * @author Kahoo
 */
public class Muur extends Figuur {
    private boolean borderMuur;
    
    public Muur() {
        super("muur", Color.DARK_GRAY);
        borderMuur = false;
    }
    public Muur(boolean borderMuur) {
        super("buitenmuur", Color.BLACK);
        this.borderMuur = borderMuur;
    }
    
    public boolean getBorderMuur() {
        return borderMuur;
    }
}
