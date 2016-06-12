package amazeing;

import java.awt.Color;

/**
 * Class Explosie acteert alleen als een tekenbaar figuur
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Explosie extends Figuur {
    
    /**
     * Instantie van dit object
     * @param vak_size_pixels = map afhankelijke maat van een vak
     * @param theme = het ingestelde theme
     */
    public Explosie(int vaksize_pixels, String theme) {
        super(Color.RED, vaksize_pixels, theme);
        initialiseerImage("explosie");
    }    
}
