package amazeing;

import java.awt.Color;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Muur extends Figuur {
    private boolean borderMuur;
    
    //Constructor
    public Muur(int vak_size_pixels, String theme) {
        super(Color.DARK_GRAY, vak_size_pixels, theme);
        borderMuur = false;
        initialiseerImage("muur");
    }
    //Constructor voor bordermuur
    public Muur(boolean borderMuur, int vak_size_pixels, String theme) {
        super(Color.BLACK, vak_size_pixels, theme);
        this.borderMuur = borderMuur;
        initialiseerImage("buitenmuur");
    }
    public boolean getBorderMuur() {
        return borderMuur;
    }
}
