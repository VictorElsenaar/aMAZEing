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
        super(Color.DARK_GRAY);
        borderMuur = false;
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels);
        this.vak_size_pixels = vak_size_pixels;
        this.theme = theme;
        initialiseerImage("muur");
    }
    //Constructor voor bordermuur
    public Muur(boolean borderMuur, int vak_size_pixels, String theme) {
        super(Color.BLACK);
        this.borderMuur = borderMuur;
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels); 
        this.vak_size_pixels = vak_size_pixels;
        this.theme = theme;
        initialiseerImage("buitenmuur");
    }
    public boolean getBorderMuur() {
        return borderMuur;
    }
}
