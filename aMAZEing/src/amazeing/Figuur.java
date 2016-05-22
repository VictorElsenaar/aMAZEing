package amazeing;

import java.awt.Color;

/**
 *
 * @author Kahoo
 */
public abstract class Figuur {

    protected Color kleur;
    protected Color kleur1;
    protected Color kleur2;

     public Figuur(Color kleur) {       
        this.kleur = kleur;
    }
    
    public Color getkleur() {
        return kleur;
    }
    public Color getkleur1() {
        return kleur1;
    }
    public Color getkleur2() {
        return kleur2;
    }
    public void setKleur1(Color kleur1) {
        this.kleur1 = kleur1;
    }
    public void setKleur2(Color kleur2) {
        this.kleur2 = kleur2;
    }
}
