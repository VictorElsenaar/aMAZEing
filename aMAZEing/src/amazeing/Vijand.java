package amazeing;

import static amazeing.AMAZEing.THEME;
import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * Class vijand, placeholder voor het figuur met bijhorende image.
 * @author Kahoo
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
