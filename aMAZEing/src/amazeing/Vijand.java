package amazeing;

import java.awt.Color;

/**
 * Class vijand, placeholder voor het figuur met bijhorende image.
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Vijand extends Figuur{
    /**
     * Nieuw instantie van dit object
     * @param vak_size_pixels = map afhankelijke maat van een vak
     * @param theme = het ingestelde theme
     */
    public Vijand(int vak_size_pixels, String theme) {
        super(Color.GREEN, vak_size_pixels, theme);
        initialiseerImage("vijand");
    }
    
}
