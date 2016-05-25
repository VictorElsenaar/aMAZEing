package amazeing;
import java.awt.Color;

/**
 *
 * @author Kahoo
 */
public abstract class Figuur {

    protected Color kleur;

     public Figuur(Color kleur) {       
        this.kleur = kleur;
    }
    
    public Color getkleur() {
        return kleur;
    }
}
