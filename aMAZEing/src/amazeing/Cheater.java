/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author vic
 */
public class Cheater extends Figuur{
    private BufferedImage cheaterImage;
    //Constructor
    public Cheater(int vak_size_pixels, String theme) {
        super(Color.RED);
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels);
        this.vak_size_pixels = vak_size_pixels;
        setVakSizePixels(vak_size_pixels);
        this.theme = theme;
        InitialiseerImage();
    }
    
    public void paint(Graphics g) {
        g.drawImage(cheaterImage.getScaledInstance(vak_size_pixels, vak_size_pixels, 0), 0, 0, null);
    }
    
    public void InitialiseerImage() {
        try {
            cheaterImage = ImageIO.read(new File("..\\\\aMAZEing\\\\src\\\\amazeing\\\\theme\\\\" + theme + "\\\\cheater.jpg"));
        } catch (Exception e) {
            
        }
    }
}
