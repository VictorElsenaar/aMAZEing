package amazeing;

import static amazeing.AMAZEing.THEME;
import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Kahoo
 */
public class Vijand extends Figuur{
    
    public Vijand(int vak_size_pixels, String theme) {
        super(Color.GREEN, vak_size_pixels, theme);
        initialiseerImage("vijand");
    }
    
}
