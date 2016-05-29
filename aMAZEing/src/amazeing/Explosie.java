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
 * @author Kahoo
 */
public class Explosie extends JComponent {
    private BufferedImage explosieImage;
    
    public Explosie() {
        setLayout(null);
        setSize(Level.global_vak_size_pixels, Level.global_vak_size_pixels); // maakt gebruik van public static global_vak_size_pixels voorlopig
        InitialiseerImage();
    }    
    public void paint(Graphics g) {
        if(explosieImage == null) {
            g.setColor(Color.RED); 
            g.fillRect(0, 0, Level.global_vak_size_pixels, Level.global_vak_size_pixels);            
        } else {
            g.drawImage(explosieImage.getScaledInstance(Level.global_vak_size_pixels,Level.global_vak_size_pixels,0), 0, 0, null); // maakt gebruik van public static global_vak_size_pixels voorlopig
        }
    }
    
    public void InitialiseerImage() {
        try {
            explosieImage = ImageIO.read(new File("..\\\\aMAZEing\\\\src\\\\amazeing\\\\theme\\\\" + THEME + "\\\\explosie.jpg")); //hardcoded voorlopig
        }
        catch (Exception e) {
            explosieImage = null;
        }
    }
}
