package amazeing;
import java.awt.Color;
import javax.swing.JComponent;

/**
 *
 * @author Kahoo
 */
public abstract class Figuur extends JComponent {

    protected Color kleur;
    protected int vak_size_pixels;
    protected String theme;
    
    public Figuur(Color kleur) {       
        this.kleur = kleur;
    }
    
    public Color getkleur() {
        return kleur;
    }
    public void setVakSizePixels(int vak_size_pixels) {
        this.vak_size_pixels = vak_size_pixels;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
}
