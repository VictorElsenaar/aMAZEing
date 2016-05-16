package amazeing;

/**
 *
 * @author Kahoo
 */
public abstract class Figuur {
    protected String naam;
    
    public Figuur(String naam) {
        this.naam = naam;
    }
    
    public String getNaam() {
        return naam;
    }
    
    public Vak getVak() {
        return null;
    }
}
