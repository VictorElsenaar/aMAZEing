package amazeing;

import java.awt.Color;

/**
 *
 * @author Kahoo
 */
public class Muur extends Figuur {
    private boolean borderMuur;
    
    public Muur() {
        super(Color.DARK_GRAY); // Color(64,64,64)
        borderMuur = false;
        kleur1 = new Color(84,84,84);
        kleur2 = new Color(94,94,94);
    }
    public Muur(boolean borderMuur) {
        super(Color.BLACK);
        this.borderMuur = borderMuur;
//        kleur1 = new Color(20,20,20);
//        kleur2 = new Color(30,30,30);
    }
    
    public boolean getBorderMuur() {
        return borderMuur;
    }
}
