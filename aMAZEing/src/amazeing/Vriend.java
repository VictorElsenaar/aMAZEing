package amazeing;

import java.awt.Color;

/**
 *
 * @author Victor
 */
public class Vriend extends Figuur {
    private boolean gehaald;
    public Vriend() {
        super(Color.MAGENTA); //Color(255,0,255)
        kleur1 = new Color(255,47,255);
        kleur2 = new Color(255,80,255);
        gehaald = false;
    }
    public void setGehaald(boolean gehaald) {
        this.gehaald = gehaald;
    }
    public boolean getGehaald() {
        return gehaald;
    }
}