package amazeing;

import java.awt.Color;

/**
 *
 * @author Kahoo
 */
public abstract class Figuur {
    protected String naam;
    protected Color kleur;
    
    public Figuur(String naam, Color kleur) {
        this.naam = naam;
        this.kleur = kleur;
    }
    
    public String getNaam() {
        return naam;
    }
    public Color getkleur() {
        return kleur;
    }
    
}
