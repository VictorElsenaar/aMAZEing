package amazeing;

import java.awt.Color;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Muur extends Figuur {
    private boolean borderMuur;
    
    /**
     * Nieuw instantie van een muur
     * @param vak_size_pixels = map afhankelijke maat van een vak
     * @param theme = het ingestelde theme
     */
    public Muur(int vak_size_pixels, String theme) {
        super(Color.DARK_GRAY, vak_size_pixels, theme);
        borderMuur = false;
        initialiseerImage("muur");
    }
    
    /**
     * Nieuw instantie van een bordermuur
     * @param borderMuur = boolean om te bepalen of dit object een border- of een normale muur is
     * @param vak_size_pixels = map afhankelijke maat van een vak
     * @param theme = het ingestelde theme
     */
    public Muur(boolean borderMuur, int vak_size_pixels, String theme) {
        super(Color.BLACK, vak_size_pixels, theme);
        this.borderMuur = borderMuur;
        initialiseerImage("buitenmuur");
    }
    public boolean getBorderMuur() {
        return borderMuur;
    }
}
