package amazeing;

import java.awt.Color;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Vriend extends Figuur {
    
    public Vriend(int vak_size_pixels, String theme) {
        super(Color.MAGENTA, vak_size_pixels, theme); 
        initialiseerImage("vriend");
    }
}