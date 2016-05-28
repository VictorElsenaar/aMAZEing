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
        setSize(50, 50); // hardcoded voorlopig
        InitialiseerImage();
    }    
    public void paint(Graphics g) {
        g.drawImage(explosieImage.getScaledInstance(50,50,0), 0, 0, null); // hardcoded voorlopig
    }
    
    public void InitialiseerImage() {
        try {
            explosieImage = ImageIO.read(new File("..\\aMAZEing\\src\\amazeing\\theme\\mario\\explosie.jpg")); //hardcoded voorlopig
        }
        catch (Exception e) {
        }
    }
}
