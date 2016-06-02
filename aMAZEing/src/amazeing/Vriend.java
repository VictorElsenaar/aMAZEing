package amazeing;

import java.awt.Color;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Vriend extends Figuur {
    
    public Vriend(int vak_size_pixels, String theme) {
        super(Color.MAGENTA); 
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels); 
        this.vak_size_pixels = vak_size_pixels;
        this.theme = theme;
        initialiseerImage("vriend");
    }
}