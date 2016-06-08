package amazeing;

import java.awt.Color;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Vriend extends Figuur {
    
    /**
     * Nieuw instantie van dit object
     * @param vak_size_pixels = map afhankelijke maat van een vak
     * @param theme = het ingestelde theme
     */
    public Vriend(int vak_size_pixels, String theme) {
        super(Color.MAGENTA, vak_size_pixels, theme); 
        initialiseerImage("vriend");
    }
}