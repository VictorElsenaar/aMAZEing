package amazeing;

import java.awt.Color;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Leeg extends Figuur{

    //Constructor
    public Leeg(int vak_size_pixels, String theme){
        super(Color.WHITE);
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels);
        this.vak_size_pixels = vak_size_pixels;
        this.theme = theme;
        initialiseerImage("leeg");
    }
}
