package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Helper extends Figuur{
    private int aantal;
    
    //Constructor
    public Helper(int vak_size_pixels, String theme) {
        super(Color.ORANGE, vak_size_pixels, theme);
        aantal = 0;
        if(debug) {aantal = 99;}
        initialiseerImage("helper");
    }
    public int getAantal() {
        return aantal;
    }
    /**
     * Helper wordt opgepakt dus wordt aantal met 1 verhoogd.
     */
    public void toevoegenAantal() {
        this.aantal++;
    }
    /**
     * Helper wordt gebruikt om de optimale route te tonen en hierdoor gaat er 1 eraf.
     */
    public void gebruik() {
        this.aantal --;
        if (debug){System.out.println("Helper - Optimale Route aantal over : " + aantal);}
    }    
}
