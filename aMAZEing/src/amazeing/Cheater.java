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
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author vic
 */
public class Cheater extends Figuur{
    private int waarde;
    
   // private BufferedImage cheaterImage;
    //Constructor
    public Cheater(int vak_size_pixels, String theme) {
        super(Color.RED);
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels);
        this.vak_size_pixels = vak_size_pixels;
        setVakSizePixels(vak_size_pixels);
        this.theme = theme;
        setWaarde();
        InitialiseerImage("cheater");
    }
    
    public int getWaarde(){
        return this.waarde;
    }
    public void setWaarde() {
        waarde = getRandomNumber(5, 15);
    }
    
    /**
     * Genereert een random getal tussen een minimum- en een maximumwaarde.
     * @param min De minimumwaarde dat een random getal mag hebben.
     * @param max De maximumwaarde dat een random getal mag hebben. 
     * @return Een random getal.
     */
    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
