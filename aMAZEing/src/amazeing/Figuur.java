package amazeing;

import static amazeing.AMAZEing.THEME;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public abstract class Figuur extends JComponent {
    protected Color kleur;
    protected int vak_size_pixels;
    protected String theme;
    protected BufferedImage image;
    
    //Constructor
    public Figuur(Color kleur, int vak_size_pixels, String theme) {       
        this.kleur = kleur;
        
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels); 
        this.vak_size_pixels = vak_size_pixels;
        this.theme = theme;
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
    public void paint(Graphics g) {
        if(image == null) {
            g.setColor(kleur); 
            g.fillRect(0, 0, vak_size_pixels, vak_size_pixels);            
        } else {
            g.drawImage(image.getScaledInstance(vak_size_pixels,vak_size_pixels,0), 0, 0, null); 
        }
    }    
    public void initialiseerImage(String naam) {
        try {
            image = ImageIO.read(new File("..\\\\aMAZEing\\\\src\\\\amazeing\\\\theme\\\\" + THEME + "\\\\"+ naam +".jpg")); 
        }
        catch (Exception e) {
            image = null;
        }
    }       
}
