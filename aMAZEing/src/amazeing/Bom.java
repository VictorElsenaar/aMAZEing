package amazeing;

import java.awt.Color;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Bom extends Figuur{
    
    /**
     * Nieuw instantie van dit object
     * @param vak_size_pixels = map afhankelijke maat van een vak
     * @param theme = het ingestelde theme
     */
    public Bom(int vak_size_pixels, String theme) {
        super(Color.RED, vak_size_pixels, theme);
        initialiseerImage("bom");
    }    
}
