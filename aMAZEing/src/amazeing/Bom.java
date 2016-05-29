
package amazeing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author Kahoo
 */
public class Bom extends JComponent{
    private BufferedImage bomImage;
    
    public Bom() {
        setLayout(null);
        setSize(Level.global_vak_size_pixels, Level.global_vak_size_pixels); // maakt gebruik van public static global_vak_size_pixels voorlopig
        InitialiseerImage();
    }    
    public void paint(Graphics g) {
        g.drawImage(bomImage.getScaledInstance(Level.global_vak_size_pixels,Level.global_vak_size_pixels,0), 0, 0, null); // maakt gebruik van public static global_vak_size_pixels voorlopig
    }
    
    public void InitialiseerImage() {
        try {
            bomImage = ImageIO.read(new File("..\\aMAZEing\\src\\amazeing\\theme\\mario\\bom.jpg")); //hardcoded voorlopig
        }
        catch (Exception e) {
        }
    }
}
