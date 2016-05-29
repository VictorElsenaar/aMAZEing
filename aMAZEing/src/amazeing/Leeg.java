package amazeing;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Kahoo
 */
public class Leeg extends Figuur{
    public Leeg(int vak_size_pixels, String theme){
        super(Color.WHITE);
        
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels);
        this.vak_size_pixels = vak_size_pixels;
        this.theme = theme;
    }
    public void paint(Graphics g) {
        g.setColor(kleur); 
        g.fillRect(0, 0, vak_size_pixels, vak_size_pixels);
    }
}
