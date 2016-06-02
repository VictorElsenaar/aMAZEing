package amazeing;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Cheater extends Figuur{
    private int waarde;
    
    //Constructor
    public Cheater(int vak_size_pixels, String theme) {
        super(Color.RED);
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels);
        this.vak_size_pixels = vak_size_pixels;
        setVakSizePixels(vak_size_pixels);
        this.theme = theme;
        setWaarde();
        initialiseerImage("cheater");
    }
    
    public int getWaarde(){
        return this.waarde;
    }
    public void setWaarde() {
        waarde = getRandomNumber(5, 15);
    }
    
    /**
     * Genereert een random getal tussen de minimum- en d maximumwaarde.
     * @param min De minimumwaarde dat een random getal mag hebben.
     * @param max De maximumwaarde dat een random getal mag hebben. 
     * @return Een random getal.
     */
    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
