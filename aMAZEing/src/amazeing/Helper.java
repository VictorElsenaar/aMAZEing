package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author vfelsenaar
 */
public class Helper extends Figuur{
    
    private int aantal;
    
    private BufferedImage helperImage;
    
    public Helper(int vak_size_pixels, String theme) {
        super(Color.ORANGE);
        aantal = 0;
        if(debug) {aantal = 99;}
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels); 
        this.vak_size_pixels = vak_size_pixels;
        this.theme = theme;
        InitialiseerImage();
    }
    public int getAantal() {
        return aantal;
    }
    public void toevoegenAantal() {
        this.aantal++;
    }
    public void gebruik() {
        this.aantal --;
        if (debug){System.out.println("Helper - Optimale Route aantal over : " + aantal);}
    }    
    
    public void paint(Graphics g) {
        if(helperImage == null) {
            g.setColor(kleur); 
            g.fillRect(0, 0, vak_size_pixels, vak_size_pixels);            
        } else {        
            g.drawImage(helperImage.getScaledInstance(vak_size_pixels,vak_size_pixels,0), 0, 0, null); 
        }
    }
    
    public void InitialiseerImage() {
        try {
            helperImage = ImageIO.read(new File("..\\\\aMAZEing\\\\src\\\\amazeing\\\\theme\\\\" + theme + "\\\\helper.jpg")); 
        }
        catch (Exception e) {
            helperImage = null;
        }
    }
}
