package amazeing;

import java.awt.Color;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Leeg extends Figuur{

    /**
     * Nieuw instantie van dit object
     * @param vak_size_pixels = map afhankelijke maat van een vak
     * @param theme = het ingestelde theme
     */
    public Leeg(int vak_size_pixels, String theme){
        super(Color.WHITE, vak_size_pixels, theme);
        initialiseerImage("leeg");
    }
}
