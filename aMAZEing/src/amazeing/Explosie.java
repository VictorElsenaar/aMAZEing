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
public class Explosie extends JComponent {
    private BufferedImage explosieImage;
    
    public Explosie() {
        setLayout(null);
        setSize(Level.temp_vak_size_pixels, Level.temp_vak_size_pixels); // maakt gebruik van public static temp_vak_size_pixels voorlopig
        InitialiseerImage();
    }    
    public void paint(Graphics g) {
        g.drawImage(explosieImage.getScaledInstance(Level.temp_vak_size_pixels,Level.temp_vak_size_pixels,0), 0, 0, null); // maakt gebruik van public static temp_vak_size_pixels voorlopig
    }
    
    public void InitialiseerImage() {
        try {
            explosieImage = ImageIO.read(new File("..\\aMAZEing\\src\\amazeing\\theme\\mario\\explosie.jpg")); //hardcoded voorlopig
        }
        catch (Exception e) {
        }
    }
}
