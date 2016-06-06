package amazeing;

import java.awt.Color;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Leeg extends Figuur{

    //Constructor
    public Leeg(int vak_size_pixels, String theme){
        super(Color.WHITE, vak_size_pixels, theme);
        initialiseerImage("leeg");
    }
}
