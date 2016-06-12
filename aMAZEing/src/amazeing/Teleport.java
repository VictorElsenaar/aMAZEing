package amazeing;

import java.awt.Color;

/**
 * Class teleport weet zijn locatieindex van doolhofMap maar heeft geen directe verbinding met doolhofMap. Tevens weet hij ook waar zijn "uitgang" is.
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Teleport extends Figuur{
    private int locationIndex;
    private Teleport other;

    /**
     * Nieuw instantie van dit object
     * @param vak_size_pixels = map afhankelijke maat van een vak
     * @param theme = het ingestelde theme
     */
    public Teleport(int vak_size_pixels, String theme) {
        super(Color.LIGHT_GRAY, vak_size_pixels, theme);
        initialiseerImage("teleport");
    }
    public void setLocationIndex(int index) {
        locationIndex = index;
    }
    public int getLocationIndex() {
        return locationIndex;
    }
    public void setOther(Teleport other) {
        this.other = other;
    }
    public Teleport getOther() {
        return other;
    }
    
}
