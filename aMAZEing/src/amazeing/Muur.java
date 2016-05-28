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
public class Muur extends Figuur {
    private boolean borderMuur;
    
    private BufferedImage buitenmuurImage;
    private BufferedImage muurImage;
    
    public Muur(int vak_size_pixels, String theme) {
        super(Color.DARK_GRAY); // Color(64,64,64)
        borderMuur = false;
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels);
        setVakSizePixels(vak_size_pixels);
        setTheme(theme);
        InitialiseerImage();
    }
    public Muur(boolean borderMuur, int vak_size_pixels, String theme) {
        super(Color.BLACK);
        this.borderMuur = borderMuur;
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels); 
        setVakSizePixels(vak_size_pixels);
        setTheme(theme);
        InitialiseerImage();
    }
    
    public boolean getBorderMuur() {
        return borderMuur;
    }
    
    
    @Override
    public void paint(Graphics g) {
        if (borderMuur) {
            g.drawImage(buitenmuurImage.getScaledInstance(vak_size_pixels,vak_size_pixels,0), 0, 0, null);
        } else {
            g.drawImage(muurImage.getScaledInstance(vak_size_pixels,vak_size_pixels,0), 0, 0, null);
        }
        
    }
    
    public void InitialiseerImage() {
        try {
            buitenmuurImage = ImageIO.read(new File("..\\\\aMAZEing\\\\src\\\\amazeing\\\\theme\\\\" + theme + "\\\\buitenmuur.jpg")); 
        }
        catch (Exception e) {
        }
        try {
            muurImage = ImageIO.read(new File("..\\\\aMAZEing\\\\src\\\\amazeing\\\\theme\\\\" + theme + "\\\\muur.jpg")); 
        }
        catch (Exception e) {
        }
    }
    
}
