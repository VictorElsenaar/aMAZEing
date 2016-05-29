package amazeing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Kahoo
 */
public class Leeg extends Figuur{
    private BufferedImage leegImage;
    public Leeg(int vak_size_pixels, String theme){
        super(Color.WHITE);
        
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels);
        this.vak_size_pixels = vak_size_pixels;
        this.theme = theme;
        InitialiseerImage();
    }
//    public void paint(Graphics g) {
//        g.setColor(kleur); 
//        g.fillRect(0, 0, vak_size_pixels, vak_size_pixels);
//    }
    public void paint(Graphics g) {
        if(leegImage == null) {
            g.setColor(kleur); 
            g.fillRect(0, 0, vak_size_pixels, vak_size_pixels);            
        } else {
            g.drawImage(leegImage.getScaledInstance(vak_size_pixels,vak_size_pixels,0), 0, 0, null); 
        }
    }
    
    public void InitialiseerImage() {
        try {
            leegImage = ImageIO.read(new File("..\\\\aMAZEing\\\\src\\\\amazeing\\\\theme\\\\" + theme + "\\\\leeg.jpg")); 
        }
        catch (Exception e) {
            leegImage = null;
        }
    }    
}
