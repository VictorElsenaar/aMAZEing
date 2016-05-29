package amazeing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Victor
 */
public class Vriend extends Figuur {
    
    private BufferedImage vriendImage;
    
    public Vriend(int vak_size_pixels, String theme) {
        super(Color.MAGENTA); //Color(255,0,255)
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels); 
        this.vak_size_pixels = vak_size_pixels;
        this.theme = theme;
        InitialiseerImage();
    }
    
    public void paint(Graphics g) {
        g.drawImage(vriendImage.getScaledInstance(vak_size_pixels,vak_size_pixels,0), 0, 0, null); 
    }
    
    public void InitialiseerImage() {
        try {
            vriendImage = ImageIO.read(new File("..\\\\aMAZEing\\\\src\\\\amazeing\\\\theme\\\\" + theme + "\\\\vriend.jpg")); //hardcoded voorlopig
        }
        catch (Exception e) {
        }
    }
    
}