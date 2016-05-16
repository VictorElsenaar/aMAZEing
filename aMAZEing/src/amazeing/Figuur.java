package amazeing;

/**
 *
 * @author Kahoo
 */
public abstract class Figuur {
    protected String naam;
    private Vak vak;
    
    public Figuur(String naam) {
        this.naam = naam;
    }
    
    public String getNaam() {
        return naam;
    }
    
    public Vak getVak() {
        return vak;
    }
    public void setVak(Vak vak) {
        this.vak = vak;
    }
}
